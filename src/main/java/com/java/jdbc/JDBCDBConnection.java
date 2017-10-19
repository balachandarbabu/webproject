package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDBConnection {
	
	public static Connection getRemoteConnection() {
	    
	      try {
	      Class.forName("com.mysql.jdbc.Driver");
	      String dbName = "FileUploadDemoDB";
	      String userName = "balachandarbabu";
	      String password = "15Oct2017";
	      String hostname = "fileuploaddemodb.cs8aingmrql4.us-east-1.rds.amazonaws.com";
	      String port = "3306";
	      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	     // logger.trace("Getting remote connection with connection string from environment variables.");
	      Connection con = DriverManager.getConnection(jdbcUrl);
	     // logger.info("Remote connection successful.");
	      return con;
	    }
	    catch (ClassNotFoundException e) { e.toString();}
	    catch (SQLException e) { e.toString();}
	    
	    return null;
	  }

}
