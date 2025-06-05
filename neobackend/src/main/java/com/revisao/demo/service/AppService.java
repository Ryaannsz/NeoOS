package com.revisao.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revisao.demo.dto.AppDTO;
import com.revisao.demo.mapper.AppMapper;
import com.revisao.demo.repository.AppRepository;

@Service
public class AppService {

    private final AppRepository repository;

    private final AppMapper mapper;

    private final IOService IOService;

    public AppService(AppRepository repository, AppMapper mapper, IOService IOService) {
	this.repository = repository;
	this.mapper = mapper;
	this.IOService = IOService;
    }

    public List<AppDTO> listApps() {
	return mapper.toDTOList(repository.findAll());
    }

    public void saveTextApp(String id) {
    }

}
