package com.ei.childcare.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ei.childcare.CarerNotFoundException;
import com.ei.childcare.controller.impl.MeetingController;
import com.ei.childcare.entity.Carer;
import com.ei.childcare.entity.Meeting;
import com.ei.childcare.model.BalanceModel;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.model.MeetingModel;
import com.ei.childcare.service.MeetingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MeetingController.class)
class MeetingControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private MeetingService meetingService;
	
	private Carer carer1;
	private Carer carer2;
	private List<Meeting> meetings;
	
	private CarerModel carerModel1;
	private CarerModel carerModel2;
	
	private MeetingModel meetingModel;
	
	private List<BalanceModel> balances;
	
	@BeforeEach
	public void initBeforeEachTest() {
		carer1 = new Carer("Ana", "Mena");
		carer2 = new Carer("Juan", "Lorca");
		
		meetings = new ArrayList<>();
		meetings.add(new Meeting(carer1, carer2, new Date(), 150, "Example observations"));
		meetings.add(new Meeting(carer2, carer1, new Date(), 75, "Example observations"));
		
		carerModel1 = new CarerModel(Long.valueOf(1), carer1.getFirstName(), carer1.getLastName());
		carerModel2 = new CarerModel(Long.valueOf(2), carer2.getFirstName(), carer2.getLastName());
		
		meetingModel = new MeetingModel(Long.valueOf(1), carerModel1.getId(), carerModel2.getId(), Integer.valueOf(150),
				"Example observations");
		
		balances = new ArrayList<>();
		balances.add(new BalanceModel(carerModel1, Integer.valueOf(150)));
		balances.add(new BalanceModel(carerModel2, Integer.valueOf(105)));
	}
	
	@Test
	void showAllMeetingsOrderByInitDateDesc() throws Exception {
		Mockito.when(meetingService.getAllMeetings()).thenReturn(meetings);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/meeting").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			fail("An error ocurred on test '/meeting' GET call: " + e);
		}
	}
	
	@Test
	void addNewMeetingToGroupList() {
		Mockito.when(meetingService.addMeeting(Mockito.<MeetingModel>any())).thenReturn(meetings.get(0));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonMeetingModel = mapper.writeValueAsString(meetingModel);
			mockMvc.perform(MockMvcRequestBuilders.post("/meeting").contentType(MediaType.APPLICATION_JSON)
					.content(jsonMeetingModel))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			fail("An error ocurred on Rest '/meeting' POST call: " + e);
		}
	}
	
	@Test
	void showBalanceGroup() {
		Mockito.when(meetingService.getCarerBalance()).thenReturn(balances);
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/meeting/balance").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			fail("An error ocurred on Rest '/meeting/balance' GET call: " + e);
		}
	}
	

	@Test
	void addNewMeetingToGroupList_BadRequest() {
		Mockito.when(meetingService.addMeeting(Mockito.<MeetingModel>any())).thenThrow(new CarerNotFoundException("Test msg"));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonMeetingModel = mapper.writeValueAsString(meetingModel);
			mockMvc.perform(MockMvcRequestBuilders.post("/meeting").contentType(MediaType.APPLICATION_JSON)
					.content(jsonMeetingModel))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		} catch (Exception e) {
			fail("An error ocurred on Rest '/meeting' POST call: " + e);
		}
	}
	
}
