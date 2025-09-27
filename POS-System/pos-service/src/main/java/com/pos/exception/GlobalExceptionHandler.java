package com.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pos.payload.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Something went wrong");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
