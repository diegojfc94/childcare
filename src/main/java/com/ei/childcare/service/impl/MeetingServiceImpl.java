package com.ei.childcare.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ei.childcare.CarerNotFoundException;
import com.ei.childcare.entity.Carer;
import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.model.MeetingModel;
import com.ei.childcare.repository.CarerRepository;
import com.ei.childcare.repository.MeetingRepository;
import com.ei.childcare.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService {

	private CarerRepository carerRepository;
	private MeetingRepository meetingRepository;
	
	public MeetingServiceImpl(CarerRepository carerRepository, MeetingRepository meetingRepository) {
		this.carerRepository = carerRepository;
		this.meetingRepository = meetingRepository;
	}

	@Override
	public List<Meeting> getAllMeetings() {
		return meetingRepository.findAllByOrderByInitDateDesc();
	}

	@Override
	public Meeting addMeeting(MeetingModel meetingModel) {
		Assert.notNull(meetingModel, "Meeting model can't be null on create process.");
		Carer carerRequester = carerRepository.findById(meetingModel.getRequesterId())
				.orElseThrow(() -> new CarerNotFoundException(String.format("The Carer requester id %d not exist on db",
						meetingModel.getRequesterId())));

		Carer carerGiver = carerRepository.findById(meetingModel.getGiverId())
				.orElseThrow(() -> new CarerNotFoundException(
						String.format("The Carer giver id %d not exist on db", meetingModel.getGiverId())));

		Meeting meeting = new Meeting(carerRequester, carerGiver, Calendar.getInstance().getTime(),
				meetingModel.getTimeRequested(), meetingModel.getObservations());

		return meetingRepository.save(meeting);
	}
	
	@Override	
	public List<BalanceModel> getCarerBalance() {
		List<BalanceModel> balances = null;

		List<Meeting> meetings = meetingRepository.findAll();
		
		Map<Long, BalanceModel> balancesByCarerId = new HashMap<>();
		for (Meeting meeting : meetings) {
			BalanceModel balanceModel = balancesByCarerId.get(meeting.getRequester().getId());
			if (balanceModel == null) {
				CarerModel requesterModel = new CarerModel(meeting.getRequester().getId(),
						meeting.getRequester().getFirstName(), meeting.getRequester().getLastName());
				balanceModel = new BalanceModel(requesterModel, -meeting.getTimeRequested());
				balancesByCarerId.put(balanceModel.getCarer().getId(), balanceModel);
			} else {
				balanceModel.setTimeRemaining(balanceModel.getTimeRemaining() - meeting.getTimeRequested());
			}
			
			balanceModel = balancesByCarerId.get(meeting.getGiver().getId());
			if (balanceModel == null) {
				CarerModel requesterModel = new CarerModel(meeting.getGiver().getId(),
						meeting.getGiver().getFirstName(), meeting.getGiver().getLastName());
				balanceModel = new BalanceModel(requesterModel, meeting.getTimeRequested());
				balancesByCarerId.put(balanceModel.getCarer().getId(), balanceModel);
			} else {
				balanceModel.setTimeRemaining(balanceModel.getTimeRemaining() + meeting.getTimeRequested());
			}
		}
		
		balances = new ArrayList<>(balancesByCarerId.values());

		return balances;
	}

}
