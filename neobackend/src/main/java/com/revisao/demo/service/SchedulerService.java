package com.revisao.demo.service;

import org.springframework.stereotype.Service;

import com.revisao.demo.repository.ProcessRepository;

@Service
public class SchedulerService {

    private final ProcessRepository repository;

    public SchedulerService(ProcessRepository repository) {
	this.repository = repository;
    }

}
