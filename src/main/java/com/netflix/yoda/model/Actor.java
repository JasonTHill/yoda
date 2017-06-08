package com.netflix.yoda.model;

import static com.netflix.yoda.Utils.verifyNotNull;
import static com.netflix.yoda.Utils.verifyNotNullOrEmpty;

public class Actor
{
	private String fullName;
	private Gender gender;
	
	public Actor(String fullName, Gender gender)
	{
		setFullName(fullName);
		setGender(gender);
	}
	
	public String getFullName()
	{
		return fullName;
	}
	
	public Actor setFullName(String fullName)
	{
		this.fullName = verifyNotNullOrEmpty(fullName, "fullName");
		return this;
	}
	
	public Gender getGender()
	{
		return gender;
	}
	
	public Actor setGender(Gender gender)
	{
		this.gender = verifyNotNull(gender, "gender");
		return this;
	}
}
