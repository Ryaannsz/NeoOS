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

    public AppService(AppRepository repository, AppMapper mapper) {
	this.repository = repository;
	this.mapper = mapper;
    }

    public List<AppDTO> listApps() {
	return mapper.toDTOList(repository.findAll());
    }

}
