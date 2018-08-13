package com.javaapp.incheon_Bot.dto;

public class MessageDTO {

	private String text;
	private PhotoDTO photo;
	private MessageButtonDTO message_button;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public PhotoDTO getPhoto() {
		return photo;
	}
	public void setPhoto(PhotoDTO photo) {
		this.photo = photo;
	}
	public MessageButtonDTO getMessage_button() {
		return message_button;
	}
	public void setMessage_button(MessageButtonDTO message_button) {
		this.message_button = message_button;
	}
	
}
