package com.rafalazar.springboot.bootcamp.app.controllers;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.springboot.bootcamp.app.documents.Enterprise;
import com.rafalazar.springboot.bootcamp.app.service.EnterpriseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/enterprise_clients")
public class EnterpriseController {
	
	@Autowired
	private EnterpriseService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Enterprise>>> list(){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.selectAll()));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Enterprise>> searchById(@PathVariable String id){
		return service.selectById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Enterprise>> create(@RequestBody Enterprise enterprise){
		if(enterprise.getJoinAt() == null) {
			enterprise.setJoinAt(new Date());
		}
		
		if(enterprise.getUpdateAt() == null) {
			enterprise.setUpdateAt(new Date());
		}
		
		return service.save(enterprise).map(p -> ResponseEntity
				.created(URI.create("/api/personal_clients/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Enterprise>> edit(@RequestBody Enterprise enterprise, @PathVariable String id){
		return service.selectById(id).flatMap(p -> {
			p.setRuc(enterprise.getRuc());
			p.setSocialName(enterprise.getSocialName());
			p.setAddress(enterprise.getAddress());
			p.setJoinAt(enterprise.getJoinAt());
			p.setUpdateAt(enterprise.getUpdateAt());
			p.setAccount(enterprise.getAccount());
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create("/api/personal_clients/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.selectById(id).flatMap(p -> {
			return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
