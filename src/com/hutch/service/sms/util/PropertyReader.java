package com.hutch.service.sms.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


import com.hutch.service.sms.util.CommonUtill;

public class PropertyReader {


	public static String read(String propertyName) 
	{
		String value = CommonUtill.EMPTY_STRING;
		Properties properties = null;
		
//		try(InputStream inputStream = new FileInputStream("/opt/apache-tomcat/webapps/HNB/conf/application.properties")){
		try(InputStream inputStream = new FileInputStream("C:\\Users\\94766\\eclipse-workspace\\HUTCH.SMS.SERVICE\\WebContent\\conf\\application.properties")){
			
			properties = new Properties();
			
			properties.load(inputStream);
			
			value = properties.getProperty(propertyName).trim();
			
		}
		catch(Exception e) 
		{
			System.out.println("Exception in PropertyReader.read " + e.toString());
		}
		finally 
		{
			if(properties != null)
				properties = null;
		}
		
		return value;
	}
}
