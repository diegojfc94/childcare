package com.ei.childcare.model;

import java.io.Serializable;

public class BalanceModel implements Serializable {

	private static final long serialVersionUID = -2436161945533907599L;
	
	private CarerModel carer;
	private Integer timeRemaining;
	
	public BalanceModel() {
	}
	
	public BalanceModel(CarerModel carer, Integer timeRemaining) {
		this.carer = carer;
		this.timeRemaining = timeRemaining;
	}
	
	public CarerModel getCarer() {
		return carer;
	}
	public void setCarer(CarerModel carer) {
		this.carer = carer;
	}
	public Integer getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(Integer minutesRemaining) {
		this.timeRemaining = minutesRemaining;
	}
	
}
