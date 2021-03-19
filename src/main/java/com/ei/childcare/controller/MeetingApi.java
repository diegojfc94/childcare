package com.ei.childcare.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.MeetingModel;

@RequestMapping("/meeting")
public interface MeetingApi {
	
	@GetMapping
	public ResponseEntity<List<Meeting>> getAllMeetings();
	

	@PostMapping()
	public ResponseEntity<Meeting> addMeeting(@RequestBody @Valid MeetingModel meetingModel);
	

	@GetMapping("/balance")
	public ResponseEntity<List<BalanceModel>> getCarerBalance();
	
}
