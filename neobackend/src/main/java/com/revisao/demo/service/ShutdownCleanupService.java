package com.revisao.demo.service;

import org.springframework.stereotype.Service;

import com.revisao.demo.repository.ProcessRepository;

import jakarta.annotation.PreDestroy;

@Service
public class ShutdownCleanupService {

    private final ProcessRepository processRepository;

    public ShutdownCleanupService(ProcessRepository processRepository) {
	this.processRepository = processRepository;
    }

    @PreDestroy
    public void onShutdown() {
	System.out.println("Aplicação encerrando... limpando processos.");
	processRepository.deleteAll();
	System.out.println("Todos os processos foram apagados.");
    }

}
