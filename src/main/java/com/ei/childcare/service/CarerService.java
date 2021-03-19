package com.ei.childcare.service;

import java.util.List;

import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;

public interface CarerService {

	public Carer addCarer(CarerModel carerModel);
	
	public List<Carer> getAllCarers();
	
}
