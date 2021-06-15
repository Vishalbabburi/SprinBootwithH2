package com.example.SpringBootH2.Exceptions;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException {
	
		public StudentNotFoundException(String message){
			super(message);
		}
	
}
