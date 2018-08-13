package com.javaapp.incheon_Bot.dto;

public class ResponseMessageDTO {

	private MessageDTO message;
	private KeyBoardDTO keyboard;
	
	public MessageDTO getMessage() {
		return message;
	}
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	public KeyBoardDTO getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(KeyBoardDTO keyboard) {
		this.keyboard = keyboard;
	}
	
}
