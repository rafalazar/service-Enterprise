package com.rafalazar.springboot.bootcamp.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Enterprise;
import com.rafalazar.springboot.bootcamp.app.service.EnterpriseService;

import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class ServiceEnterpriseApplication implements CommandLineRunner {

	@Autowired
	private EnterpriseService service;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(ServiceEnterpriseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceEnterpriseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("accounts").subscribe();
		mongoTemplate.dropCollection("enterprise_client").subscribe();
		
		Account corriente = new Account("123456", "Corriente");
		Account ahorro = new Account("654321", "Ahorro");
		Account fijo = new Account("875467", "Fijo");
		
		Flux.just(corriente, ahorro, fijo).flatMap(a -> service.saveAccount(a)).doOnNext(a -> {
			log.info("Cuentas creadas: " + a.getId() + " - " + a.getTypeAccount() );
		}).thenMany(
				Flux.just(
						new Enterprise("4525313424","Everis","Av. Flora Tristan",fijo),
						new Enterprise("3467313563","BINARIA","Av. Larcomar",ahorro),
						new Enterprise("2345612345","Utec","Jr. Medrano Silva",corriente))
				.flatMap(enterprise -> {
					enterprise.setJoinAt(new Date());
					enterprise.setUpdateAt(new Date());
					return service.save(enterprise);
				}))
		.subscribe(enterprise -> log.info("Insert: " + enterprise.getId() + " - " + enterprise.getSocialName()));
		
	}

}
