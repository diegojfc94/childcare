package com.ei.childcare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "id_requester", referencedColumnName = "id")
	private Carer requester;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "id_giver", referencedColumnName = "id")
	private Carer giver;
	
	@NotNull
	@Column
	private Date initDate;
		
	@NotNull
	@Min(1)
	@Column
	private Integer timeRequested;
	
	@Column
	private String observations;
	
	public Meeting() {
	}
	
	public Meeting(Carer requester, Carer giver, Date initDate, Integer timeRequested, String observations) {
		this.requester = requester;
		this.giver = giver;
		this.initDate = initDate;
		this.timeRequested = timeRequested;
		this.observations = observations;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Carer getRequester() {
		return requester;
	}
	
	public void setRequester(Carer requester) {
		this.requester = requester;
	}
	
	public Carer getGiver() {
		return giver;
	}
	
	public void setGiver(Carer giver) {
		this.giver = giver;
	}
	
	public Date getInitDate() {
		return initDate;
	}
	
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
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
