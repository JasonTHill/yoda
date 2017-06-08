package com.netflix.yoda;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import com.netflix.yoda.config.DaoConfig;

@SpringBootApplication
@Import(DaoConfig.class)
public class Yoda
{
	@Bean
	public static PropertyPlaceholderConfigurer props() throws IOException
	{
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
		props.setLocations(new ClassPathResource("properties/jdbc.properties"));
		return props;
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(Yoda.class, args);
	}
}
