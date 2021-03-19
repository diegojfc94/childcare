package com.ei.childcare.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ei.childcare.entity.Carer;
import com.ei.childcare.model.CarerModel;

@RequestMapping("/carer")
public interface CarerApi {

	@PostMapping
	public ResponseEntity<Carer> addCarer(@RequestBody @Valid CarerModel carerModel);
	
	@GetMapping
	public ResponseEntity<List<Carer>> getCarers();
	
}
