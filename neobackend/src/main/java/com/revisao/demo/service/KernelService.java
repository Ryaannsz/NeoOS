package com.revisao.demo.service;

import org.springframework.stereotype.Service;

import com.revisao.demo.dto.ProcessDTO;
import com.revisao.demo.models.App;
import com.revisao.demo.repository.AppRepository;

@Service
public class KernelService {

    private final ProcessService processService;

    private final AppRepository appRepository;

    public KernelService(ProcessService processService, AppRepository appRepository) {
	this.processService = processService;
	this.appRepository = appRepository;
    }

    public ProcessDTO lauchApp(String id) {
	App e = appRepository.findById(id).orElseThrow(() -> new RuntimeException("App não encontrado"));
	return processService.createProcess(e);
    }

}
