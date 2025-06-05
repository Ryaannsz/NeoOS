package com.revisao.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.models.App;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.AppRepository;
import com.revisao.demo.repository.ProcessRepository;

@Service
public class KernelService {

    private final ProcessService processService;

    private final ProcessRepository processRepository;

    private final AppRepository appRepository;

    private final IOService IOService;

    private final static long TIMEOUT = 7000;

    private final static long INTERVAL_CHECK = 200;

    public KernelService(ProcessRepository processRepository, IOService IOService, ProcessService processService,
	    AppRepository appRepository) {
	this.processRepository = processRepository;
	this.IOService = IOService;
	this.processService = processService;
	this.appRepository = appRepository;
    }

    public ProcessEntity getProcessByAppId(String appId, String instanceName) {
	ProcessEntity process = processRepository.findByApp_IdAndInstanceName(appId, instanceName)
		.orElseThrow(() -> new RuntimeException("Processo não encontrado com appID: " + appId));
	return process;
    }

    public void saveFile(String appId, Map<String, Object> payload, String TYPE) {

	String instanceName = (String) payload.get("instanceName");

	ProcessEntity process = getProcessByAppId(appId, instanceName);

	if (!(process.getState().equals(StateProcess.RUNNING)))
	    return;

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.WAITING);
	process.setWaitingReason("DISK_IO_SAVE: " + fileName);
	processRepository.saveAndFlush(process);
	IOService.diskSave(process.getId());

    }

    public void closeApp(String appId, Map<String, Object> payload, String TYPE) throws UnsupportedActionException {

	String instanceName = (String) payload.get("instanceName");

	ProcessEntity process = getProcessByAppId(appId, instanceName);

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

    public void openApp(String appId, Map<String, Object> payload, String TYPE) {

	String instanceName = (String) payload.get("instanceName");

	App app = appRepository.findById(appId)
		.orElseThrow(() -> new RuntimeException("App não encontrado com appID: " + appId));

	ProcessEntity process = processService.createProcess(app, instanceName);

	long startTime = System.currentTimeMillis();

	while (true) {
	    ProcessEntity updatedProcess = processRepository.findById(process.getId())
		    .orElseThrow(() -> new RuntimeException("Processo sumiu do banco"));

	    if (updatedProcess.getState() == StateProcess.READY) {
		break;
	    }

	    if (System.currentTimeMillis() - startTime > TIMEOUT) {
		throw new RuntimeException("Timeout: Processo não foi movido para READY a tempo");
	    }

	    try {
		Thread.sleep(INTERVAL_CHECK);
	    } catch (InterruptedException e) {
		Thread.currentThread().interrupt();
		throw new RuntimeException("Thread interrompida", e);
	    }
	}

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.RUNNING);
	processRepository.save(process);

    }

}
