package br.com.restwith.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
	
	private Date timesTemp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timesTemp, String message, String details) {
		this.timesTemp = timesTemp;
		this.message = message;
		this.details = details;
	}

	public Date getTimesTemp() {
		return timesTemp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
	

}
