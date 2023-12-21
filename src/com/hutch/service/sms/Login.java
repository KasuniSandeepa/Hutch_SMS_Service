package com.hutch.service.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;






public class Login {
	
	  public static final Logger logger = Logger.getLogger(Login.class.getName());
	   // private static Connection connection;
	    public boolean userLogin(String username,String password){
	    	boolean st = false;
			//Connection con = null;

	        try{
	            Connection	con = DatabaseConnection.getInstance().getConnection();
	            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user WHERE username =? and password=?");
	            preparedStatement.setString(1,username);
	            preparedStatement.setString(2,password);
               
	            System.out.println(username);
	            ResultSet rs = preparedStatement.executeQuery();
	            st  = rs.next();

	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.log(Level.SEVERE, e.getMessage());

	        }


	        return st;
	    }

}
