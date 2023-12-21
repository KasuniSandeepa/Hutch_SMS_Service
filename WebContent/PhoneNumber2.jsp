<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hutch.service.sms.SMSSender" %>
<%@ page import="com.hutch.service.sms.util.CommonUtill" %>
<%@ page import="com.hutch.service.sms.model.ResponseMessage" %>
<%@ page import="java.util.UUID"%>
<%@page import="com.mobios.util.LogUtil"%>
<%@page import="com.hutch.service.sms.SendSMSThread"%>
<%@ page import="com.hutch.service.sms.util.PropertyReader" %>
<%@ page import="com.hutch.service.sms.QueryController" %>
<%@ page import="com.hutch.service.sms.model.USIMBase" %>




<%
	boolean isCallInserted = false;
	boolean isRespSMSInserted = false;
	boolean isCalledStausUpdated = false;
	int responseSMSStatus = 0;
	String msgName = "SOLO";
	String smsApiResponse = CommonUtill.EMPTY_STRING;
	ResponseMessage responseMessage = null;	
	
	String cli = CommonUtill.EMPTY_STRING + request.getParameter("ani");
	String serv	= "94788100100";
	String mask	= "1312";
	
	String serverIP = CommonUtill.EMPTY_STRING + request.getLocalAddr();
	String ip = CommonUtill.EMPTY_STRING + request.getRemoteAddr();
	String softVersion = "V1.0";
	String platformID = "EVI-ETI";
	String uuid = CommonUtill.EMPTY_STRING + UUID.randomUUID();
	String logData = CommonUtill.EMPTY_STRING + serverIP + "," + serv + "," + ip + "," + softVersion + "," + platformID + "," + uuid;
	
	LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string:" + serv + ",");
	
		
	LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string=" + serv + ",unclean_cli="+cli+",unclean_serv="+serv);
	if(cli.length()==10){
		cli = "94"+cli.substring(1, cli.length());
	}if(cli.length()==9){
		cli = "94"+cli;
	}
	
	//serv = "94"+serv.substring(1, serv.length());
	
	
	LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string=" + serv + ",unclean_cli="+cli+",unclean_serv="+serv+",clean_cli="+cli+",");
	
	try
	{
		String callInsertQuery = "INSERT INTO `incoming_call` (`msisdn`, `serv`, `called_date_time`, `uuid`) VALUES ('"+cli+"', '"+serv+"', NOW(), '"+uuid+"')";
		//System.out.println("callInsertQuery :" + callInsertQuery);
		isCallInserted = QueryController.InsertData(callInsertQuery);
	}
	catch(Exception e)
	{
		//System.out.println("Error when QueryController.InsertData , " + e.toString());
		LogUtil.getLog("Event").debug(logData + ",ERROR,exception_when_callInsertQuery,");
		LogUtil.getLog("Error").debug(logData + ",ERROR,exception_when_validating_mobile_numbers,exception_when_callInsertQuery,exception="+e.toString()+",");
	}
	
	USIMBase usimBase = null;
	/*try
	{
		String checkInBaseQuery = "SELECT `msisdn`, `called_status`, `reload_status`, `unicode_status`, `post_paid_status`, `updated_date_time` FROM base_hutch  WHERE `msisdn` = '"+cli+"'";
		usimBase = QueryController.getMobileFromUsimBase(checkInBaseQuery);
	}
	catch(Exception e)
	{
		System.out.println("Error when QueryController.getMobileFromUsimBase " + e.toString());
		LogUtil.getLog("Event").debug(logData + ",ERROR,exception_when_checkInBaseQuery,");
		LogUtil.getLog("Error").debug(logData + ",ERROR,exception_when_validating_mobile_numbers,exception_when_checkInBaseQuery,exception="+e.toString()+",");
	}*/	
	LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string=" + serv + ",unclean_cli="+cli+",unclean_serv="+serv+"clean_cli="+cli+",callInsertQuery_status="+isCallInserted+",");
	
	//if(usimBase!=null){
		//msgName="PAWNING_F";
		System.out.println("Feathre phone msgName:"+msgName);
		String updateCalledStatusQuery = "UPDATE base_hutch  SET `called_status` = `called_status` + 1, `updated_date_time` = NOW()  WHERE `msisdn` = '"+cli+"'";
		isCalledStausUpdated = QueryController.InsertData(updateCalledStatusQuery);
		
		LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string=" + serv + ",unclean_cli="+cli+",unclean_serv="+serv+"clean_cli="+cli+",callInsertQuery_status="+isCallInserted+",078_072,selected_base=,exsists_in_the_base,update_called_status="+isCalledStausUpdated+",");
	
	//}
	
	try
	{
		String getMsgQuery = "SELECT `id`,`msg_content` FROM `response_message` WHERE `msg_name` = '"+msgName+"'";
		responseMessage = QueryController.getResponseMsg(getMsgQuery);
	}
	catch(Exception e)
	{
		System.out.println("Error when QueryController.getResponseMsg , " + e.toString());
		LogUtil.getLog("Event").debug(logData + ",ERROR,exception_when_getMsgQuery,");
		LogUtil.getLog("Error").debug(logData + ",ERROR,exception_when_validating_mobile_numbers,exception_when_getMsgQuery,exception="+e.toString()+",");
	}

	LogUtil.getLog("Event").debug(logData + ",Call_status:Received,number_string=" + serv + ",unclean_cli="+cli+",unclean_serv="+serv+"clean_cli="+cli+",callInsertQuery_status="+isCallInserted+",update_called_status="+isCalledStausUpdated+",message_id="+responseMessage.getMessageId()+",");	
		
	SendSMSThread sendSMSThread = new SendSMSThread(cli, responseMessage.getMessageContent(), logData, responseMessage.getMessageId(), uuid, mask);
	Thread smsThread = new Thread(sendSMSThread);
	smsThread.start();
	
%>