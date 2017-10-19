package com.java.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.beans.Login;
import com.java.jdbc.JDBCDBConnection;

@Controller
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	@RequestMapping("/login")
	public String login(Map<String, Object> model, @ModelAttribute Login login,HttpServletRequest request,
			HttpServletResponse response) {
		String redirectPage = "fileUpload";
		login.getPassword();
		login.getUserName();
		
		
		Connection conn = null;
		  Statement setupStatement = null;


		  try {
		    // Create connection to RDS DB instance
		    conn = JDBCDBConnection.getRemoteConnection();
		    Statement readStatement = null;
			  ResultSet resultSet = null;
		    // Create a table and write two rows
		    setupStatement = conn.createStatement();
		    String selectQuery = "Select user_id from user_profile where user_id = ? and user_password = ?";
		    PreparedStatement prepStmt = conn.prepareStatement(selectQuery);
		    prepStmt.setString(1, login.getUserName());
		    prepStmt.setString(2, login.getPassword());
		    
		   
		    logger.info(selectQuery);
		    resultSet = prepStmt.executeQuery();
		    logger.info(prepStmt.getFetchSize());
		   // String insertRow1 = "INSERT INTO Beanstalk (Resource) VALUES ('EC2 Instance');";
		   // String
		    
		    
		    
		    logger.info(resultSet.first());
		    
		    while (resultSet.next()) {              

	            System.out.println(resultSet.getString("user_id"));
	            System.out.println(resultSet.getString("user_password"));
	           /* System.out.println(resultSet.getString("Col 3"));                    
	            System.out.println(resultSet.getString("Col n"));*/
	    }
		    if(!resultSet.first()){
		    	model.put("message", "error");
		    	redirectPage = "error";
		    }
		    else{
		    	HttpSession session = request.getSession();
				session.setAttribute("user", login.getUserName());
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user", login.getUserName());
				userName.setMaxAge(30*60);
				response.addCookie(userName);
		    }
		  } catch (SQLException ex) {
			    // Handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			  } finally {
			       System.out.println("Closing the connection.");
			      if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
			  }
		
		  
		return "forward:/getUserContentById";
	}
	
}
