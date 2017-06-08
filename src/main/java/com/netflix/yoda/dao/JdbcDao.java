package com.netflix.yoda.dao;

import static com.netflix.yoda.Utils.verifyNotNullOrEmpty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.netflix.yoda.CheckedFunction;

public abstract class JdbcDao<T>
{
	private final String connectionUrl;
	private final String user;
	private final String password;
	
	public JdbcDao(String connectionUrl, String user, String password)
	{
		this.connectionUrl = verifyNotNullOrEmpty(connectionUrl, "connectionUrl");
		this.user = verifyNotNullOrEmpty(user, "user");
		this.password = verifyNotNullOrEmpty(password, "password");
	}
	
	public List<T> executeQuery(String query, CheckedFunction<ResultSet, List<T>, SQLException> mapper) throws SQLException
	{
		try(Connection con = DriverManager.getConnection(connectionUrl, user, password))
		{
			return mapper.apply(con.createStatement().executeQuery(query));
		}
	}
	
	public List<T> executePreparedQuery(String query, List<String> params, CheckedFunction<ResultSet, List<T>, SQLException> mapper) throws SQLException
	{
		try(Connection con = DriverManager.getConnection(connectionUrl, user, password))
		{
			PreparedStatement stmt = con.prepareStatement(query);
			for(int i = 0; i < params.size(); i++)
			{
				stmt.setString(i + 1, params.get(i));
			}
			
			return mapper.apply(stmt.executeQuery());
		}
	}
}
