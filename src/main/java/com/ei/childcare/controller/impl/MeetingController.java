package com.ei.childcare.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ei.childcare.controller.MeetingApi;
import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.MeetingModel;
import com.ei.childcare.service.MeetingService;

@RestController
@Validated
public class MeetingController implements MeetingApi {

	private MeetingService meetingService;
	
	public MeetingController(MeetingService meetingService) {
		this.meetingService = meetingService;
	}

	@Override
	public ResponseEntity<List<Meeting>> getAllMeetings() {
		return new ResponseEntity<>(meetingService.getAllMeetings(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Meeting> addMeeting(@RequestBody @Valid MeetingModel meetingModel) {
		return new ResponseEntity<>(meetingService.addMeeting(meetingModel), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<BalanceModel>> getCarerBalance(){
		return new ResponseEntity<>(meetingService.getCarerBalance(), HttpStatus.OK);
	}
	
}
