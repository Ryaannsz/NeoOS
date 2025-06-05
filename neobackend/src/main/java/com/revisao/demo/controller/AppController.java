package com.revisao.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.demo.dto.AppDTO;
import com.revisao.demo.dto.UserActionRequest;
import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.service.AppService;
import com.revisao.demo.service.UserActionHandlerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("app")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AppController {

    private final AppService service;

    private final UserActionHandlerService handler;

    @GetMapping
    public ResponseEntity<List<AppDTO>> getAllApps() {
	return ResponseEntity.ok(service.listApps());
    }

    @PostMapping("/{appId}")
    public ResponseEntity<Void> actionsApp(@PathVariable String appId, @Valid @RequestBody UserActionRequest data)
	    throws UnsupportedActionException {
	handler.handleAction(appId, data);
	return ResponseEntity.ok().build();
    }

}
