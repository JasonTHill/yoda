package com.netflix.yoda.model;

import static com.netflix.yoda.Utils.verifyNotNull;
import static com.netflix.yoda.Utils.verifyNotNullOrEmpty;

import java.util.Collections;
import java.util.List;

public class Movie
{
	private String title;
	private String releaseYear;
	private String imdbRating;
	
	private List<Actor> cast;
	
	public Movie(String title, String releaseYear, String imdbRating)
	{
		this(title, releaseYear, imdbRating, Collections.emptyList());
	}
	
	public Movie(String title, String releaseYear, String imdbRating, List<Actor> cast)
	{
		setTitle(title);
		setReleaseYear(releaseYear);
		setImdbRating(imdbRating);
		setCast(cast);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Movie setTitle(String title)
	{
		this.title = verifyNotNullOrEmpty(title, "title");
		return this;
	}
	
	public String getReleaseYear()
	{
		return releaseYear;
	}
	
	public Movie setReleaseYear(String releaseYear)
	{
		this.releaseYear = verifyNotNullOrEmpty(releaseYear, "releaseYear");
		return this;
	}
	
	public String getImdbRating()
	{
		return imdbRating;
	}
	
	public Movie setImdbRating(String imdbRating)
	{
		this.imdbRating = verifyNotNullOrEmpty(imdbRating, "imdbRating");
		return this;
	}
	
	public List<Actor> getCast()
	{
		return cast;
	}
	
	public Movie setCast(List<Actor> cast)
	{
		this.cast = verifyNotNull(cast, "cast");
		return this;
	}
}
