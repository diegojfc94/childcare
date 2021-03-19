package com.ei.childcare.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CarerModel implements Serializable {

	private static final long serialVersionUID = -2893484339961596072L;

	private Long id;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;

	public CarerModel() {
	}
	
	public CarerModel(Long id, String firstName, String lastName) {
		this.id = id;
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
