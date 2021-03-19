package com.ei.childcare.service;

import java.util.List;

import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.MeetingModel;

public interface MeetingService {

	public List<Meeting> getAllMeetings();
	
	public Meeting addMeeting(MeetingModel meetingModel);
	
	public List<BalanceModel> getCarerBalance();
	
}
