package com.example.SpringBootH2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<ErrorResponseObject>  studentexceptionhandler(StudentNotFoundException exc){
		 //create an object for ErrorResponseObject class using its all args constructor
		ErrorResponseObject err=new ErrorResponseObject(HttpStatus.NOT_FOUND.value(),
														exc.getMessage(),
														System.currentTimeMillis());
		
		//wrap this custom error object in a ResponseEntity object for fine grained access of http response and return it
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	// handler for all other general exceptions thrown by controllers in entire application
		@ExceptionHandler
		public ResponseEntity<ErrorResponseObject> errorhandler(Exception exc) {
			
			ErrorResponseObject error=new ErrorResponseObject(HttpStatus.BAD_REQUEST.value(),
																exc.getMessage(),
																System.currentTimeMillis());
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
}
