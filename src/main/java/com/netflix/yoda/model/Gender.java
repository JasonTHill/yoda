package com.netflix.yoda.model;

public enum Gender
{
	M("Male"), F("Female"), U("Unknown");
	
	private String display;
	private Gender(String display)
	{
		this.display = display;
	}
	
	public String getDisplay()
	{
		return display;
	}
}
