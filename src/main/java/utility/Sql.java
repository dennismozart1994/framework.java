package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
	private static String user = "root";
	private static String pwd = "";
	private static String host = "localhost";
	private static String port = "80";
	private static Connection connection;
	
	// start connection
	private static Statement Connect(String database) throws SQLException
	{
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", user, pwd);
		Statement s = connection.createStatement();
		return s;
	}
	
	// executes sql and return retrived data
	public static ResultSet ExecuteSQL(String database, String sql) throws SQLException
	{
		ResultSet res = Connect(database).executeQuery(sql);
		res.next();
		return res;
	}
	
	public static void closeConnection() throws SQLException
	{
		if(connection!= null)
		{
			connection.close();
		}
	}
	
	public static void goToTheNextLineResult(ResultSet queryResult) throws SQLException
	{
		queryResult.next();
	}
	
	// only as a reference method to get multiple lines of a query result, shouldn't be used into real event
	// actually for each project it should have a dataObject qith personal methods to capture data
	public static void getResults(ResultSet queryResult) throws SQLException
	{
		while(queryResult.next())
		{
			// use the getters to get the fields that you want to get.
		}
	}
	/************************************** GET METHODS *********************************************/
	public static String getStringField(ResultSet queryResult, String fieldName) throws SQLException
	{
		return queryResult.getString(fieldName);
	}
	
	public static Integer getIntegerField(ResultSet queryResult, String fieldName) throws SQLException
	{
		return queryResult.getInt(fieldName);
	}
	
	public static Float getFloatField(ResultSet queryResult, String fieldName) throws SQLException
	{
		return queryResult.getFloat(fieldName);
	}
	
	public static Double getDoubleField(ResultSet queryResult, String fieldName) throws SQLException
	{
		return queryResult.getDouble(fieldName);
	}
	
	public static Boolean getBooleanField(ResultSet queryResult, String fieldName) throws SQLException
	{
		return queryResult.getBoolean(fieldName);
	}
}
