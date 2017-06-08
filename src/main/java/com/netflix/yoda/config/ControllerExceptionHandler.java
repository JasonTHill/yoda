package com.netflix.yoda.config;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netflix.yoda.WebServiceError;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler
{
	private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public WebServiceError handleSqlException(SQLException e)
	{
		String message = "Bad request!";
		logger.error(message, e);
		return new WebServiceError(message, HttpStatus.BAD_REQUEST, e);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public WebServiceError handleSqlException(Exception e)
	{
		String message = "An uknown error occured!";
		logger.error(message, e);
		return new WebServiceError(message, HttpStatus.INTERNAL_SERVER_ERROR, e);
	}
}
