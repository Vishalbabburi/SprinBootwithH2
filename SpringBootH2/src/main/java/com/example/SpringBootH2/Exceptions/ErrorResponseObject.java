package com.example.SpringBootH2.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseObject {
	private int status;
	private String message;
	private long timestamp;
}
