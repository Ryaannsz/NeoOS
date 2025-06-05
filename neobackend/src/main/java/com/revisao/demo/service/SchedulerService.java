package com.revisao.demo.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.revisao.demo.dto.AppDTO;
import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.mapper.AppMapper;
import com.revisao.demo.models.App;
import com.revisao.demo.models.ProcessEntity;

@Service
public class SchedulerService {

    private final AppService appService;

    private final ProcessService processService;

    private final AppMapper appMapper;

    private static final long SIMULATED_TIME_SLICE_MS = 5000;

    public SchedulerService(AppService appService, ProcessService processService, AppMapper appMapper) {
	this.appService = appService;
	this.processService = processService;
	this.appMapper = appMapper;
    }

    @Scheduled(fixedRate = SIMULATED_TIME_SLICE_MS)
    public void runScheduler() {

	List<AppDTO> apps = appService.listApps();

	for (AppDTO app : apps) {
	    App e = appMapper.toEntity(app);
	    boolean exists = processService.existsApp(e);
	    if (!exists) {
		processService.createProcess(e);
	    }
	}

	List<ProcessEntity> newProcesses = processService.findByState(StateProcess.NEW);

	for (ProcessEntity process : newProcesses) {
	    process.setState(StateProcess.READY);
	    processService.save(process);
	    System.out.println("Processo " + process.getId() + " movido para READY");
	}

    }

}
