package com.javaapp.incheon_Bot.dto;

public class KeyBoardDTO {

	private String type;
	private String[] buttons;
	 
	public KeyBoardDTO(String[] buttons) {
		this.type = "buttons";
		this.buttons = buttons;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getButtons() {
		return buttons;
	}

	public void setButtons(String[] buttons) {
		this.buttons = buttons;
	}
}
