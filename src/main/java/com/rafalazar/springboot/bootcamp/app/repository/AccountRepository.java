package com.rafalazar.springboot.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.springboot.bootcamp.app.documents.Account;

public interface AccountRepository extends ReactiveMongoRepository<Account, String>{

}
