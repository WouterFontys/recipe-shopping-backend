package com.musthavecaffeine.recipeapp.controllers.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.musthavecaffeine.recipeapp.services.exceptions.ResourceNotFoundException;
import com.musthavecaffeine.recipeapp.services.exceptions.UnauthorizedException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {

		return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ UnauthorizedException.class })
	public ResponseEntity<Object> handleUnauthorizedException(Exception exception, WebRequest request) {

		return new ResponseEntity<Object>("Unauthorized", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}
}
