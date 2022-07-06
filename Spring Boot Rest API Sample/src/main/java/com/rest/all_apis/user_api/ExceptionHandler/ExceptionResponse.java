package com.rest.all_apis.user_api.ExceptionHandler;

import java.util.Date;

public class ExceptionResponse {
	Date Timestamp;
	String Message;
	String Details;
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		Timestamp = timestamp;
		Message = message;
		Details = details;
	}
	public Date getTimestamp() {
		return Timestamp;
	}
	public String getMessage() {
		return Message;
	}
	public String getDetails() {
		return Details;
	}
}
