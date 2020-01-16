package com.rafalazar.springboot.bootcamp.app.service;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Enterprise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnterpriseService {

	public Flux<Enterprise> selectAll();

	public Mono<Enterprise> selectById(String id);

	public Mono<Enterprise> save(Enterprise enterprise);

	public Mono<Void> delete(Enterprise enterprise);

	public Mono<Enterprise> findByRuc(String ruc);

	public Flux<Account> findAllAcount();

	public Mono<Account> findAccountById(String id);

	public Mono<Account> saveAccount(Account account);

}
