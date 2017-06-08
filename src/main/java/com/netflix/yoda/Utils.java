package com.netflix.yoda;

public class Utils
{
	public static <T> T verifyNotNull(T nullable, String message)
	{
		if(nullable == null)
		{
			throw new IllegalArgumentException(message + " must not be null!");
		}
		return nullable;
	}
	
	public static String verifyNotNullOrEmpty(String nullable, String message)
	{
		if(verifyNotNull(nullable, message).trim().isEmpty())
		{
			throw new IllegalArgumentException(message + " must not be empty!");
		}
		return nullable;
	}
}
