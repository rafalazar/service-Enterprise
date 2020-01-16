package com.rafalazar.springboot.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.springboot.bootcamp.app.documents.Enterprise;

import reactor.core.publisher.Mono;

public interface EnterpriseRepository extends ReactiveMongoRepository<Enterprise, String>{
	
	Mono<Enterprise> findByRuc(String ruc);

}
