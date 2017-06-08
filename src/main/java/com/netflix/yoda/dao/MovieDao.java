package com.netflix.yoda.dao;

import static com.netflix.yoda.Utils.verifyNotNull;
import static java.util.stream.Collectors.toList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.netflix.yoda.model.Movie;
import com.netflix.yoda.model.Actor;
import com.netflix.yoda.model.Gender;

public class MovieDao extends JdbcDao<Movie>
{
	public MovieDao(String connectionUrl, String user, String password)
	{
		super(connectionUrl, user, password);
	}
	
	public List<Movie> findMovies(String title) throws SQLException
	{
		verifyNotNull(title, "title");
		
		return executePreparedQuery("select movies.movieid, movies.title, movies.year, ratings.rank, GROUP_CONCAT(CONCAT_WS('::', actors.name, actors.sex) SEPARATOR ';;') "
								  + "from movies "
								  + "inner join ratings on movies.movieid = ratings.movieid "
								  + "inner join movies2actors on movies.movieid = movies2actors.movieid "
								  + "inner join actors on movies2actors.actorid = actors.actorid "
								  + "where movies.title like ? "
								  + "group by movies.movieid",
								  Arrays.asList("%" + title + "%"),
								  rs ->
		{
			List<Movie> results = new ArrayList<>();
			while(rs.next())
			{
				Movie movie = new Movie(rs.getString(2), rs.getString(3), rs.getString(4));
				movie.setCast(Stream.of(rs.getString(5).split(";;"))
									.map(actors ->
									{
										String[] actorInfo = actors.split("::");
										if(actorInfo.length == 2)
										{
											try
											{
												return new Actor(actorInfo[0], Gender.valueOf(actorInfo[1]));
											}
											catch(IllegalArgumentException e)
											{ // If, for some reason, the gender cannot be converted to an enum
												return new Actor(actorInfo[0], Gender.U);
											}
										}
										return new Actor(actorInfo[0], Gender.U);
									})
									.collect(toList()));
				results.add(movie);
			}
			return results;
		});
	}
}
