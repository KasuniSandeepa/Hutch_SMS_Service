package com.hutch.service.sms.util;

import java.io.FileWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.mobios.util.LogUtil;

public class CommonUtillMethod 
{	
	
	public static boolean writeToFile(String msisdnString, String fileName, String logData) 
	{	
		FileWriter fw = null;
		
		boolean isFileWrited = false;
		try 
		{
			fw = new FileWriter(fileName);
			fw.write(msisdnString);
			fw.close();
			
			isFileWrited = true;
		} 
		catch (Exception e) 
		{
			LogUtil.getLog("Event").debug(logData + ",ERROR,CommonUtilMethods.writeToFile,");
			LogUtil.getLog("Error").debug(logData + ",ERROR,CommonUtilMethods.writeToFile,");
		}
		finally
		{
			if(fw != null)
				fw = null;
		}

		return isFileWrited;
	}
	
	public static String getCommaSeparatedStringFromArray(String[] emailList)
	{
		String lineSeparatedString = CommonUtill.EMPTY_STRING;
		
		for(String line : emailList) 
		{
			lineSeparatedString = lineSeparatedString + line + ",";
		}
		
		return lineSeparatedString;
	}
	
	
	
	public static String getEncodedMsg(String url) throws Exception 
	{

		String encoded_url = CommonUtill.EMPTY_STRING;

		try 
		{
			encoded_url = URLEncoder.encode(url, "UTF-8");
			
			
		} 
		catch (Exception e) 
		{
			System.out.println("Exception when getEncodedURL " + e.toString());
		}

		return encoded_url;
	}

	public static String getDecodedMsg(String url) 
	{

		String decoded_url = CommonUtill.EMPTY_STRING;

		try 
		{
			decoded_url = URLDecoder.decode(url, "UTF-8");
			System.out.println("decode" +decoded_url);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Exception when getDecodedURL " + e.toString());
		}

		return decoded_url;
	}
}
