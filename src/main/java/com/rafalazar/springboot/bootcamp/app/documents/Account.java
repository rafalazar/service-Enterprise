package com.rafalazar.springboot.bootcamp.app.documents;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Document(collection="accounts")
public class Account {
	
	@Id
	private String id;
	private String numberAccount;
	private String typeAccount;
	
	public Account() {
		
	}
	
	public Account(String numberAccount, String typeAccount) {
		this.numberAccount = numberAccount;
		this.typeAccount = typeAccount;
	}

}
