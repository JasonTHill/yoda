package com.netflix.yoda;

import static com.netflix.yoda.Utils.verifyNotNull;
import static com.netflix.yoda.Utils.verifyNotNullOrEmpty;

import java.util.Optional;

import org.springframework.http.HttpStatus;

public class WebServiceError
{
	private final String message;
	private final HttpStatus status;
	private final Optional<Exception> exception;
	
	public WebServiceError(String message, HttpStatus status, Exception e)
	{
		this.message = verifyNotNullOrEmpty(message, "message");
		this.status = verifyNotNull(status, "HTTP status");
		this.exception = Optional.ofNullable(e);
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public HttpStatus getStatus()
	{
		return status;
	}
	
	public String getFormattedException()
	{
		return exception.map(e -> "Message: " + e.getMessage() +
								  ", Method: " + e.getStackTrace()[0].getClassName() + "." + e.getStackTrace()[0].getMethodName())
						.orElse("None");
	}
}
