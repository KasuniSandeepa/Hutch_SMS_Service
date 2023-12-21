package com.hutch.service.sms.model;

public class USIMBase {
	
	private String msisdn;
	private int calledStatus;
	private int reloadStatus;
	private String unicodeStatus;
	private String postPaidStatus;
	private String dateTime;
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getUnicodeStatus() {
		return unicodeStatus;
	}
	public void setUnicodeStatus(String unicodeStatus) {
		this.unicodeStatus = unicodeStatus;
	}
	public String getPostPaidStatus() {
		return postPaidStatus;
	}
	public void setPostPaidStatus(String postPaidStatus) {
		this.postPaidStatus = postPaidStatus;
	}
	public int getReloadStatus() {
		return reloadStatus;
	}
	public int reloadQueryPart() {
		return reloadStatus;
	}
	public void setReloadStatus(int reloadStatus) {
		this.reloadStatus = reloadStatus;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public int getCalledStatus() {
		return calledStatus;
	}
	public void setCalledStatus(int calledStatus) {
		this.calledStatus = calledStatus;
	}

}
