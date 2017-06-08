package com.netflix.yoda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.yoda.dao.MovieDao;

@Configuration
public class DaoConfig
{
	@Bean
	public MovieDao movieDao(@Value("${jdbc.mysql.connection.url}") String connectionUrl,
							 @Value("${jdbc.mysql.user}") String user,
							 @Value("${jdbc.mysql.password}") String password)
	{
		return new MovieDao(connectionUrl, user, password);
	}
}
