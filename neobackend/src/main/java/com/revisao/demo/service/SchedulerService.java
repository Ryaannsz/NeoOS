package com.revisao.demo.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;

@Service
public class SchedulerService {

    private final ProcessService processService;

    private static final long SIMULATED_TIME_SLICE_MS = 5000;

    public SchedulerService(ProcessService processService) {
	this.processService = processService;
    }

    @Scheduled(fixedRate = SIMULATED_TIME_SLICE_MS)
    public void runScheduler() {

	List<ProcessEntity> newProcesses = processService.findByState(StateProcess.NEW);

	for (ProcessEntity process : newProcesses) {
	    process.setState(StateProcess.READY);
	    processService.save(process);
	    System.out.println("Processo " + process.getId() + " movido para READY");
	}

    }

}
