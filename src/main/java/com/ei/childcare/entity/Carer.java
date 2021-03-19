package com.ei.childcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Carer {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column
	private String firstName;
	
	@NotNull
	@Column
	private String lastName;
	
	public Carer() {
	}
	
	public Carer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
}
