package com.revisao.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.demo.dto.ProcessDTO;
import com.revisao.demo.service.ProcessService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("process")
public class ProcessController {

    private final ProcessService service;

    @GetMapping
    public ResponseEntity<List<ProcessDTO>> getAllProcess() {
	return ResponseEntity.ok(service.listProcess());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcessById(@PathVariable String id) {
	return ResponseEntity.ok(service.getProcessById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProcessDTO> terminateProcess(@PathVariable String id) {
	return ResponseEntity.ok(service.terminateProcess(id));
    }

}
