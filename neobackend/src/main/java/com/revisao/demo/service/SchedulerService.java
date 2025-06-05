package com.revisao.demo.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

@Service
public class SchedulerService {

    private final ProcessRepository repository;

    private static final long SIMULATED_TIME_SLICE_MS = 5000;

    public SchedulerService(ProcessRepository repository) {
	this.repository = repository;
    }

    @Scheduled(fixedRate = SIMULATED_TIME_SLICE_MS)
    public void runScheduler() {
	System.out.println("Scheduler rodando...");

	List<ProcessEntity> newProcesses = repository.findByState(StateProcess.NEW);

	for (ProcessEntity process : newProcesses) {
	    process.setState(StateProcess.READY);
	    repository.save(process);
	    System.out.println("Processo " + process.getId() + " movido para READY");
	}

	/*
	 * List<ProcessEntity> readyProcess =
	 * repository.findByStateOrderByDateCreationAsc(StateProcess.READY);
	 * 
	 * if (readyProcess.isEmpty()) return;
	 * 
	 * ProcessEntity nextProcess = readyProcess.get(0);
	 * 
	 * nextProcess.setState(StateProcess.RUNNING); repository.save(nextProcess);
	 * System.out.println("Processo " + nextProcess.getId() +
	 * " movido para RUNNING");
	 */

    }

}
