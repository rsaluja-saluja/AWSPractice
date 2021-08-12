package com.example.model;

import java.math.BigDecimal;

public class PersonRequest {

	private BigDecimal id;
	
	private String firstName;
	private String lastName;
	
	
	
	
	public PersonRequest() {
		super();
	}

	public PersonRequest(BigDecimal id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		
		this.id = id;
	}
}
