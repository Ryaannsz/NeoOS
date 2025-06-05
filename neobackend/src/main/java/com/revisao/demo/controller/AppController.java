package com.revisao.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.demo.dto.AppDTO;
import com.revisao.demo.service.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("app")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AppController {

    private final AppService service;

    @GetMapping
    public ResponseEntity<List<AppDTO>> getAllApps() {
	return ResponseEntity.ok(service.listApps());
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<Void> saveApp(@PathVariable String id) {
	service.saveTextApp(id);
	return ResponseEntity.ok().build();
    }

}
