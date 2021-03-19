package com.ei.childcare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.repository.CarerRepository;
import com.ei.childcare.service.CarerService;

@Service
public class CarerServiceImpl implements CarerService {

	private CarerRepository carerRepository;

	public CarerServiceImpl(CarerRepository carerRepository) {
		this.carerRepository = carerRepository;
	}

	@Override
	public Carer addCarer(CarerModel carerModel) {
		Assert.notNull(carerModel, "Carer model can't be null on create process.");
		
		Carer carer = new Carer(carerModel.getFirstName(), carerModel.getLastName());

		return carerRepository.save(carer);
	}

	@Override
	public List<Carer> getAllCarers() {
		return carerRepository.findAll();
	}

}
