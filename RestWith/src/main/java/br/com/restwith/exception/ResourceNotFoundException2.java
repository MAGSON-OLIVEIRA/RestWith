package br.com.restwith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jdk.jshell.spi.ExecutionControl.RunException;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ResourceNotFoundException2 extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException2(String ex) {
		super(ex);
	}

}
