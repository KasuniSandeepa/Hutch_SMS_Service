package com.hutch.service.sms;

import java.util.Date;
import java.util.UUID;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


import com.hutch.service.sms.util.CommonUtill;
import com.hutch.service.sms.util.PropertyReader;
import com.mobios.util.LogUtil;

public class DatabaseConnection {
	

	public static final DatabaseConnection instancD = new DatabaseConnection();

	public static DatabaseConnection getInstance() 
	{			
		return instancD;
	}

	public Connection getConnection() throws SQLException 
	{
		
		String now = new Date().toString();
		String softVersion = "V1.0";
		String platformID = "EVI-ETI";
		String uuid = CommonUtill.EMPTY_STRING + UUID.randomUUID();
		String logData = CommonUtill.EMPTY_STRING + now + "," + softVersion + "," + platformID + "," + uuid;
		
		Connection con = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/hutch?useUniocde=yes&characterEncoding=UTF-8";
		String dbUser = PropertyReader.read("db_username");
		String dbPass = PropertyReader.read("db_password");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con =  DriverManager.getConnection(dbUrl, dbUser, dbPass);
			
			LogUtil.getLog("Event").debug(logData + ",DatabaseConnection.getConnection,user="+dbUser+",con="+con+",");
			LogUtil.getLog("Connectionlog").debug(logData + ",DatabaseConnection.getConnection,user="+dbUser+",con="+con+",");
			
			return con;
		} catch (Exception e) 
		{
			System.out.println("DB error : " + e.toString());
			LogUtil.getLog("Event").debug(logData + ",ERROR,DatabaseConnection.getConnection,user="+dbUser+",con="+con+",exception="+e);
			LogUtil.getLog("Event").debug(logData + ",ERROR,DatabaseConnection.getConnection,user="+dbUser+",con="+con+",exception="+e);
		}
		
		return con;
	}

}
