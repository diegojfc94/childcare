package com.ei.childcare;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Carer not found")
public class CarerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8298594328460558765L;

	public CarerNotFoundException (String msg) {
		super(msg);
	}
	
}
