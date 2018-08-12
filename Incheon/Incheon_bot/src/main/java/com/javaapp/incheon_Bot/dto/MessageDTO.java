package com.javaapp.incheon_Bot.dto;

public class MessageDTO {

	private String text;
	private PhotoDTO photo;
	private MessageButtonDTO messageButtonDTO;
	
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
	public MessageButtonDTO getMessageButtonDTO() {
		return messageButtonDTO;
	}
	public void setMessageButtonDTO(MessageButtonDTO messageButtonDTO) {
		this.messageButtonDTO = messageButtonDTO;
	}
	
}
