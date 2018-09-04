package com.javaapp.incheon_Bot.dto;

public class NoticeDTO {
	private int mNum;
	private String mContent;
	private int mHits;
	
	public NoticeDTO(){
		
	}
	
	public NoticeDTO(int mNum, String mContent,int mHits) {
		this.mNum = mNum;
		this.mContent = mContent;
		this.mHits = mHits;
	}
	
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public int getmHits() {
		return mHits;
	}
	public void setmHits(int mHits) {
		this.mHits = mHits;
	}
}
