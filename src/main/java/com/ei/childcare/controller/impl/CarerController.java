package com.ei.childcare.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ei.childcare.controller.CarerApi;
import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;
import com.ei.childcare.service.CarerService;

@RestController
@Validated
public class CarerController implements CarerApi {

	private CarerService carerService;
	
	public CarerController(CarerService carerService) {
		this.carerService = carerService;
	}
	
	@Override
	public ResponseEntity<Carer> addCarer(@RequestBody @Valid CarerModel carerModel) {
		return new ResponseEntity<>(carerService.addCarer(carerModel), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Carer>> getCarers() {
		return new ResponseEntity<>(carerService.getAllCarers(), HttpStatus.OK);
	}
	
}
