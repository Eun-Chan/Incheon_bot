package com.javaapp.incheon_Bot.dto;

public class messageDTO {
	private String user_key;
	private String content;
	private String type;
	
	
	@Override
	public String toString() {
		return "user_Key = " +user_key+ ", content : " + content + ", type : " +type;
	}
}
