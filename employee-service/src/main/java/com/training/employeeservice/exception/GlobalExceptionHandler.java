package com.training.employeeservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		ResponseError error =new ResponseError(ex.getMessage(),404,LocalDate.now());
		return new ResponseEntity<ResponseError>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
	{
	String name=ex.getName();
	String  excepted=ex.getRequiredType().getSimpleName();
	String value=(String) ex.getValue();
	String message=String.format("%s should be valid %s isnt %s ", name,excepted,value);
	return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
}
