package com.ei.childcare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ei.childcare.entity.Carer;
import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.model.MeetingModel;
import com.ei.childcare.repository.CarerRepository;
import com.ei.childcare.repository.MeetingRepository;
import com.ei.childcare.service.impl.MeetingServiceImpl;

@ExtendWith(MockitoExtension.class)
class MeetingServiceTest {

	@Mock
	private CarerRepository carerRepository;
	
	@Mock
	private MeetingRepository meetingRepository;
	
	private Carer carer1;
	private Carer carer2;
	private Meeting meeting;
	
	private CarerModel carerModel1;
	private CarerModel carerModel2;
	
	private MeetingModel meetingModel;
	
	private List<BalanceModel> balances;
	
	@BeforeEach
	public void initBeforeEachTest() {
		carer1 = new Carer("Ana", "Mena");
		carer1.setId(Long.valueOf(1));
		carer2 = new Carer("Juan", "Lorca");
		carer2.setId(Long.valueOf(2));
		
		meeting = new Meeting(carer1, carer2, new Date(), 150, "Example observations");
		meeting.setId(Long.valueOf(1));
		
		carerModel1 = new CarerModel(Long.valueOf(1), carer1.getFirstName(), carer1.getLastName());
		carerModel2 = new CarerModel(Long.valueOf(2), carer2.getFirstName(), carer2.getLastName());
		
		meetingModel = new MeetingModel(Long.valueOf(1), carerModel1.getId(), carerModel2.getId(), Integer.valueOf(150),
				"Example observations");
		
		balances = new ArrayList<>();
		balances.add(new BalanceModel(carerModel1, Integer.valueOf(150)));
	}
	
	@Test
	void getAllMeetings() {
		Mockito.when(meetingRepository.findAllByOrderByInitDateDesc()).thenReturn(List.of(meeting));
		
		MeetingService meetingService = new MeetingServiceImpl(carerRepository, meetingRepository);
		List<Meeting> meetings = meetingService.getAllMeetings();
		
		assertFalse(meetings.isEmpty());
		assertEquals(meeting.getGiver(), meetings.get(0).getGiver());
		assertEquals(meeting.getRequester(), meetings.get(0).getRequester());
		assertEquals(meeting.getInitDate(), meetings.get(0).getInitDate());
		assertEquals(meeting.getTimeRequested(), meetings.get(0).getTimeRequested());
	}

	@Test
	void addNewMeeting() {
		Mockito.when(meetingRepository.save(Mockito.<Meeting>any())).thenReturn(meeting);
		Mockito.when(carerRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(carer1));
		
		MeetingService meetingService = new MeetingServiceImpl(carerRepository, meetingRepository);
		Meeting result = meetingService.addMeeting(meetingModel);
		
		assertNotNull(result);
		assertEquals(meeting.getGiver(), result.getGiver());
		assertEquals(meeting.getInitDate(), result.getInitDate());
		assertEquals(meeting.getTimeRequested(), result.getTimeRequested());
		assertEquals(meeting.getObservations(), result.getObservations());
		assertEquals(meeting.getRequester(), result.getRequester());
	}

	@Test
	void getCarerBalance() {
		Mockito.when(meetingRepository.findAll()).thenReturn(List.of(meeting));
		
		MeetingService meetingService = new MeetingServiceImpl(carerRepository, meetingRepository);
		List<BalanceModel> balancesResult = meetingService.getCarerBalance();
		
		assertFalse(balancesResult.isEmpty());
		assertEquals(balances.get(0).getCarer().getFirstName(), balancesResult.get(0).getCarer().getFirstName());
		assertEquals(balances.get(0).getCarer().getLastName(), balancesResult.get(0).getCarer().getLastName());
		assertEquals(Math.negateExact(balances.get(0).getTimeRemaining()), balancesResult.get(0).getTimeRemaining());
		
	}
	
}
