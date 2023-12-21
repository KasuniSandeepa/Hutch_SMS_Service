package com.hutch.service.sms;



import com.hutch.service.sms.util.CommonUtill;
import com.hutch.service.sms.util.CommonUtillMethod;
import com.mobios.util.LogUtil;


public class SendSMSThread implements Runnable 
{

	private String msisdn;
	private String msg;
	private String logData;
	private int msgId;
	private String uuid;
	private String mask;

	public SendSMSThread(String msisdn, String msg, String logData, int msgId, String uuid, String mask) 
	{
		super();
		this.msisdn = msisdn;
		this.msg = msg;
		this.logData = logData;
		this.msgId = msgId;
		this.uuid = uuid;
		this.mask = mask;
	}

	@Override
	public void run() 
	{	
		LogUtil.getLog("Event").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",");
		LogUtil.getLog("SMSlog").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",");
		
		boolean isApiRespInserted = false;
		boolean isRespSMSInserted = false;
		int msgSentStatus = 1;
		String response = CommonUtill.EMPTY_STRING;
		String encodedMsg = CommonUtill.EMPTY_STRING;
		String decodedMsg = CommonUtill.EMPTY_STRING;
		String refId = CommonUtill.EMPTY_STRING;
		
		try 
		{
			
			encodedMsg = CommonUtillMethod.getEncodedMsg(msg);
			decodedMsg =CommonUtillMethod.getDecodedMsg(encodedMsg);
		
		} 
		catch (Exception e) 
		{
		
		}
		
		LogUtil.getLog("Event").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",message_encoded,");
		
		try 
		{
			System.out.println("_______mask________ " + mask);
			response = SMSSender.sendSMS("", msisdn, decodedMsg, mask);
		} 
		catch (Exception e) 
		{
			msgSentStatus = 0;
		}
		
		LogUtil.getLog("Event").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",message_encoded,sms_api_called_response="+response+",");
		LogUtil.getLog("SMSlog").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",message_encoded,sms_api_called_response="+response+",");
		
		try 
		{
			
			String insertApiRespQuery = "INSERT INTO `sms_api_response`(`uuid`, `response`, `inserted_date_time`) VALUES ('"+uuid+"', '"+response+"', NOW())";
			isApiRespInserted = QueryController.InsertData(insertApiRespQuery);
		} 
		catch (Exception e) 
		{
			System.out.println("Error when QueryController.InsertData(insertSentSMSQuery) , " + e.toString());
		}
		
		LogUtil.getLog("Event").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",message_encoded,sms_api_called_response="+response+",insert_api_resp_status="+isApiRespInserted+",");
		
		if (response.trim().startsWith("SUCCESS"))
		{
			msgSentStatus = 1;			
			refId = response.split(":")[2].trim();
		}
				
		try 
		{
			String insertSentSMSQuery = "INSERT INTO `sent_messages`(`msisdn`, `message_id`, `sent_staus`, `uuid`, `inserted_date_time`, `reference_id`) VALUES ('"+ msisdn + "', " + msgId + ", '" + msgSentStatus + "', '" + uuid + "', NOW(), '" + refId + "')";
			isRespSMSInserted = QueryController.InsertData(insertSentSMSQuery);
		} 
		catch (Exception e) 
		{
			System.out.println("Error when QueryController.InsertData(insertSentSMSQuery) , " + e.toString());
		}
		
		LogUtil.getLog("Event").debug(logData + ",SendSMSThread:run,thread_started,cli="+msisdn+",msgId="+msgId+",message_encoded,sms_api_called_response="+response+",insert_api_resp_status="+isApiRespInserted+",insert_sent_sms_status="+isRespSMSInserted+",");
	}
}
