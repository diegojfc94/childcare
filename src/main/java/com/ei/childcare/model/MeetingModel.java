package com.ei.childcare.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeetingModel implements Serializable {

	private static final long serialVersionUID = 9212132208211128331L;
	
	private Long id;
	@NotNull
	private Long requesterId;
	@NotNull
	private Long giverId;
	@NotNull
	@Min(1)
	private Integer timeRequested;
	private String observations;

	public MeetingModel() {
	}
		
	public MeetingModel(Long id, Long requesterId, Long giverId, Integer timeRequested,
			String observations) {
		this.id = id;
		this.requesterId = requesterId;
		this.giverId = giverId;
		this.timeRequested = timeRequested;
		this.observations = observations;
	}
	
	public Long getId() {		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}
	public Long getGiverId() {
		return giverId;
	}
	public void setGiverId(Long giverId) {
		this.giverId = giverId;
	}
	public Integer getTimeRequested() {
		return timeRequested;
	}
	public void setTimeRequested(Integer minutes) {
		this.timeRequested = minutes;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
}
