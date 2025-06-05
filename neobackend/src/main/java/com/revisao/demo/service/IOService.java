package com.revisao.demo.service;

import org.springframework.stereotype.Service;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

@Service
public class IOService {

    private ProcessRepository processRepository;

    public IOService(ProcessRepository processRepository) {
	this.processRepository = processRepository;
    }

    public void diskSave(String id) {
	ProcessEntity e = processRepository.findById(id).orElseThrow(() -> new RuntimeException("Processo não achado"));

	e.setState(StateProcess.WAITING);

	try {
	    Thread.sleep(5000);
	} catch (InterruptedException err) {
	    Thread.currentThread().interrupt();
	}

	e.setState(StateProcess.RUNNING);
	e.setWaitingReason(null);
	processRepository.save(e);
	System.out.println("Processo " + id + ": I/O concluído. Movido para RUNNING.");
    }

}
