package com.test.employee.exception.advices;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.employee.exceptions.EmployeeException;

@ControllerAdvice
public class EmployeeNotFoundAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<Object> handleCityNotFoundException(
			EmployeeException ex, WebRequest request) 
	{
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("errorMessage:", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
