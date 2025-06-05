package com.revisao.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revisao.demo.service.BaseServiceImpl;

import jakarta.validation.Valid;

public abstract class BaseController<D, T, ID> {

	protected final BaseServiceImpl<D, T, ID> service;
	
	protected BaseController(BaseServiceImpl<D, T, ID> service) {
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity<D> create(@Valid @RequestBody D entity){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
	}
	
	@GetMapping
	public ResponseEntity<List<D>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<D>> findById(@PathVariable ID id){	
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable ID id, @Valid @RequestBody D entity){
		service.update(id, entity);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable ID id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
