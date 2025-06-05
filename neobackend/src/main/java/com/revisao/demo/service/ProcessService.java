package com.revisao.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revisao.demo.dto.ProcessDTO;
import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.mapper.ProcessMapper;
import com.revisao.demo.models.App;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

@Service
public class ProcessService {

    private final ProcessMapper mapper;

    private final ProcessRepository repository;

    public ProcessService(ProcessMapper mapper, ProcessRepository repository) {
	this.mapper = mapper;
	this.repository = repository;
    }

    public ProcessDTO createProcess(App app) {
	ProcessEntity e = new ProcessEntity();
	e.setName(app.getName() + "-process");
	e.setPriority(0);
	e.setState(StateProcess.NEW);
	e.setApp(app);
	repository.saveAndFlush(e);
	return mapper.toDTO(e);
    }

    public List<ProcessDTO> listProcess() {
	return mapper.toDTOList(repository.findAll());
    }

    public ProcessDTO getProcessById(String id) {
	return mapper.toDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Processo não achado")));
    }

    public ProcessDTO terminateProcess(String id) {
	ProcessEntity e = repository.findById(id).orElseThrow(() -> new RuntimeException("Processo não achado"));
	e.setState(StateProcess.TERMINATED);
	return mapper.toDTO(e);
    }

    public boolean existsApp(App app) {
	return repository.existsByApp(app);
    }

    public List<ProcessEntity> findByState(StateProcess state) {
	return repository.findByState(state);
    }

    public ProcessEntity save(ProcessEntity e) {
	return repository.save(e);
    }

}
