package com.netflix.yoda.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.yoda.dao.MovieDao;
import com.netflix.yoda.model.Movie;

@RestController
public class MovieController
{
	@Autowired MovieDao movieDao;
	
	@GetMapping("/movies")
	public List<Movie> getMovies(@RequestParam(value = "title", required = false, defaultValue = "") String title) throws SQLException
	{
		System.out.println(title);
		return movieDao.findMovies(title);
	}
}
