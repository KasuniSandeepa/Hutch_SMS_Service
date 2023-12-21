package com.hutch.service.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import com.hutch.service.sms.util.CommonUtill;
import com.hutch.service.sms.util.PropertyReader;
import com.mobios.util.LogUtil;

public class SMSSender {
	public static String sendDataToEMail(String numberString, String fileName, String type, String subject, String toParties, String ccParties, String logData) throws Exception 
	{
		int httpCode = 0;
		String reqJson = null;
		String response = null;
		
		BufferedReader rd = null;
		InputStreamReader inputStrm = null;
		URL smsUrll = null;
		HttpURLConnection connection = null;
		DataOutputStream wr = null;
		BufferedWriter writer = null;
		StringBuilder content = null;
	
		try 
		{
			
			String line;
			smsUrll = new URL("http://213.136.79.138:8080/SendMail/sendEmail.jsp");
			reqJson = "{\r\n" + 
					"	\"fileName\":\""+fileName+"\",\r\n" + 
					"	\"type\":\""+type+"\",\r\n" + 
					"	\"msisdn\":\""+numberString+"\",\r\n" + 
					"	\"subject\":\""+subject+"\",\r\n" + 
					"	\"toParties\":\""+toParties+"\",\r\n" + 
					"	\"ccParties\":\""+ccParties+"\"\r\n" + 
					"}";
			
			connection = (HttpURLConnection) smsUrll.openConnection();			
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod("POST");			
			
			try 
			{
				wr = new DataOutputStream(connection.getOutputStream());
			}
			catch(Exception e)
			{
				LogUtil.getLog("Event").debug(logData + ",ERROR,HTTPClient.sendDataToEMail_getOutputStream,"+e.toString()+",");
				LogUtil.getLog("Error").debug(logData + ",ERROR,HTTPClient.sendDataToEMail_getOutputStream,"+e.toString()+",");
				System.out.println("Exception thrown by getOutputStream()");
			}
			
			writer = new BufferedWriter(new OutputStreamWriter((OutputStream) wr, "UTF-8"));
			
			writer.write(reqJson);
			writer.close();
			wr.flush();
			wr.close();
			
			httpCode = connection.getResponseCode();
			System.out.println("Http Code : " + httpCode);
			inputStrm = httpCode >= 400 ? new InputStreamReader(connection.getErrorStream())
					: new InputStreamReader(connection.getInputStream());
			rd = new BufferedReader(inputStrm);
			content = new StringBuilder();
			
			while ((line = rd.readLine()) != null) 
			{
				content.append(line);
				content.append("\n");
			}
			
			response = content.toString();
					
			LogUtil.getLog("Event").debug(logData + ",HTTPClient.sendDataToEMail,sent_email_date_to_213_response_code="+httpCode+",");
	
		} 
		catch(Exception e) 
		{
			LogUtil.getLog("Event").debug(logData + ",ERROR,HTTPClient.sendDataToEMail,"+e.toString()+",");
			LogUtil.getLog("Error").debug(logData + ",ERROR,HTTPClient.sendDataToEMail,"+e.toString()+",");
			System.out.println("Exception in HTTPClientSMS.sendDataToEMail()  " + e.toString());
			throw new Exception("Exception");
		}finally {
			try {
				if(rd != null)
					rd.close();
				if(inputStrm != null)
					inputStrm.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			try {
				if(inputStrm != null)
					inputStrm.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if(wr != null)
				wr = null;
			
			if(writer != null)
				writer = null;
			
			if(content != null)
				content = null;
			
			if(smsUrll != null)
				smsUrll = null;
		}
		
		return response;
	}

	public static String sendSMS(String logData, String cli, String message, String mask) throws Exception{
		
		String username = PropertyReader.read("sms_username");
		String password = PropertyReader.read("sms_password");
		String url = PropertyReader.read("sms_URL");
		
		BufferedReader in = null;
		StringBuffer responses = null;
		URL obj = null;
		HttpURLConnection con = null;
		String inputLine;
		String response = CommonUtill.EMPTY_STRING;
	//	url = url + "?USER="+username+"&PWD="+password+"&MASK="+mask+"&NUM="+cli+"&MSG="+message+"";
	//	url = url + "?username="+username+"&password="+password+"&src="+mask+"&dst="+cli+"&msg="+message+"";
		System.out.println("This is the message body"+message);
		
	//	http://sms.textware.lk:5000/sms/send_sms.php?username=softlogic_1312&password=Soft1212GC&&src=1312&dst=94767097773&msg=Thisura
		try
		{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
		
			// optional default is GET
			con.setRequestMethod("GET");
		
			int responseCode = con.getResponseCode();

			System.out.println("Response Code : " + responseCode);
		
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			responses = new StringBuffer();
		
			while ((inputLine = in.readLine()) != null) 
			{
				responses.append(inputLine);
			}
			
			in.close();
			
			response = responses.toString();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e.toString());
		}
		finally
		{
			if(con != null)
				con = null;
			
			if(responses != null)
				responses = null;
			
			if(obj != null)
				obj = null;
			
			if(in != null)
				in = null;
		}
		
		return response;
	}
}

