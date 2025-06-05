package com.revisao.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

@Service
public class KernelService {

    private final ProcessRepository processRepository;

    private final IOService IOService;

    public KernelService(ProcessRepository processRepository, IOService IOService) {
	this.processRepository = processRepository;
	this.IOService = IOService;
    }

    public void saveFile(ProcessEntity process, Map<String, Object> payload, String TYPE) {

	if (!(process.getState().equals(StateProcess.RUNNING)))
	    return;

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.WAITING);
	process.setWaitingReason("DISK_IO_SAVE: " + fileName);
	processRepository.saveAndFlush(process);
	IOService.diskSave(process.getId());

    }

    public void closeApp(ProcessEntity process, Map<String, Object> payload, String TYPE)
	    throws UnsupportedActionException {

	if (process.getState().equals(StateProcess.WAITING))
	    throw new UnsupportedActionException("Tentativa de fechar aplicativo enquanto está sendo salvo");

	if (!(process.getState().equals(StateProcess.RUNNING)))
	    return;

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.TERMINATED);
	process.setWaitingReason(null);
	processRepository.save(process);
    }

    public void openApp(ProcessEntity process, Map<String, Object> payload, String TYPE) {
	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.RUNNING);
	processRepository.save(process);

    }

}
