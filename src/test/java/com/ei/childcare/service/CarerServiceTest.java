package com.ei.childcare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.repository.CarerRepository;
import com.ei.childcare.service.impl.CarerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CarerServiceTest {

	@Mock
	private CarerRepository carerRepository;
	
	private Carer carer;
	
	private CarerModel carerModel;
	
	@BeforeEach
	public void initBeforeEachTest() {
		carer = new Carer("Ana", "Mena");
		carer.setId(Long.valueOf(1));
		
		carerModel = new CarerModel(Long.valueOf(1), carer.getFirstName(), carer.getLastName());
	}
	

	@Test
	void addNewCarer() {
		Mockito.when(carerRepository.save(Mockito.<Carer>any())).thenReturn(carer);
		
		CarerService careService = new CarerServiceImpl(carerRepository);
		Carer result = careService.addCarer(carerModel);
		
		assertNotNull(result);
		assertEquals(carer.getFirstName(), result.getFirstName());
		assertEquals(carer.getLastName(), result.getLastName());
	}
	
	@Test
	void getAllCarers() {
		Mockito.when(carerRepository.findAll()).thenReturn(List.of(carer));
		
		CarerService careService = new CarerServiceImpl(carerRepository);
		List<Carer> result = careService.getAllCarers();
		
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(result.size(), 1);
		assertEquals(carer.getFirstName(), result.get(0).getFirstName());
		assertEquals(carer.getLastName(), result.get(0).getLastName());
	}
	
}
