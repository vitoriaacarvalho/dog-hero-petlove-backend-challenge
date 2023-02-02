package com.vitoria.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseStatusException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	HttpStatus http;
	String errorMessage;
	
	public ResponseStatusException(HttpStatus http, String errorMessage) {
       this.http=http;
       this.errorMessage=errorMessage;
    }
	
}
