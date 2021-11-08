package com.sunil.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
reason= "Invalid Page Number Provided in API Call")
public class InvalidPageNumberException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidPageNumberException(String message) {
		super(message);
	}
}
