package com.javaapp.incheon_Bot.dto;

import java.util.ArrayList;

public class KeyBoardDTO {

	private String type;
	private ArrayList<String> arrayList;
	 
	public KeyBoardDTO(ArrayList<String> arrayList) {
		this.type = "buttons";
		this.arrayList = arrayList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<String> arrayList) {
		this.arrayList = arrayList;
	}
}