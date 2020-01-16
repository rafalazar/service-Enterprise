package com.rafalazar.springboot.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Enterprise;
import com.rafalazar.springboot.bootcamp.app.repository.AccountRepository;
import com.rafalazar.springboot.bootcamp.app.repository.EnterpriseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	EnterpriseRepository enterpriseRepo;

	@Autowired
	AccountRepository accountRepo;

	@Override
	public Flux<Enterprise> selectAll() {
		return enterpriseRepo.findAll();
	}

	@Override
	public Mono<Enterprise> selectById(String id) {
		return enterpriseRepo.findById(id);
	}

	@Override
	public Mono<Enterprise> save(Enterprise enterprise) {
		return enterpriseRepo.save(enterprise);
	}

	@Override
	public Mono<Void> delete(Enterprise enterprise) {
		return enterpriseRepo.delete(enterprise);
	}

	@Override
	public Mono<Enterprise> findByRuc(String ruc) {
		return enterpriseRepo.findByRuc(ruc);
	}

	@Override
	public Flux<Account> findAllAcount() {
		return accountRepo.findAll();
	}

	@Override
	public Mono<Account> findAccountById(String id) {
		return accountRepo.findById(id);
	}

	@Override
	public Mono<Account> saveAccount(Account account) {
		return accountRepo.save(account);
	}

}
