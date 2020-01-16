package com.rafalazar.springboot.bootcamp.app.documents;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="enterprise_client")
public class Enterprise {
	
	@Id
	private String id;
	@NotEmpty
	private String ruc;
	@NotEmpty
	private String socialName;
	@NotEmpty
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
	private Account account;
	
	public Enterprise() {
		
	}
	
	public Enterprise(String ruc, String socialName, String address, Account account){
		this.ruc = ruc;
		this.socialName = socialName;
		this.address = address;
		this.account = account;
	}

}
