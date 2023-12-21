package com.hutch.service.sms.model;

public class ResponseMessage {
	
	
	private int messageId;
	private String messageContent;
	
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageContent() {
		return  messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	@Override
	public String toString() {
		return "ResponseMessage [messageId=" + messageId + ", messageContent=" + messageContent + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
