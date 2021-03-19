package com.ei.childcare.controller;

import static org.junit.jupiter.api.Assertions.fail;

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

import com.ei.childcare.controller.impl.CarerController;
import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.service.CarerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarerController.class)
class CarerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private CarerService meetingService;
	
	private Carer carer;
	
	private CarerModel carerModel;
	
	@BeforeEach
	public void initBeforeEachTest() {
		carer = new Carer("Ana", "Mena");
		
		carerModel = new CarerModel(Long.valueOf(1), carer.getFirstName(), carer.getLastName());
	}

	@Test
	void addNewCarerToGroup() {
		Mockito.when(meetingService.addCarer(Mockito.<CarerModel>any())).thenReturn(carer);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonCarerModel = mapper.writeValueAsString(carerModel);
			mockMvc.perform(MockMvcRequestBuilders.post("/carer").contentType(MediaType.APPLICATION_JSON)
					.content(jsonCarerModel))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			fail("An error ocurred on Rest '/carer' POST call: " + e);
		}
	}
	
	@Test
	void getAllCarers() {
		Mockito.when(meetingService.getAllCarers()).thenReturn(List.of(carer));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonCarerModel = mapper.writeValueAsString(carerModel);
			mockMvc.perform(MockMvcRequestBuilders.get("/carer").contentType(MediaType.APPLICATION_JSON)
					.content(jsonCarerModel))
			.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			fail("An error ocurred on Rest '/carer' POST call: " + e);
		}
	}
}
