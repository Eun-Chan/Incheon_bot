package com.javaapp.incheon_Bot.command.help;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class HelpCommand implements ICommand{

	@Override
	public String execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		
		String str = "사용법\n";
		str = "(밥) 학식 메뉴 -> 식당 메뉴를 알려드립니다\n";
		str += "(해) 인천 날씨 -> 인천 날씨를 알려드립니다 (빠른 시일내에 세부적인 날씨 상태로 업데이트)\n";
		str += "(음표) 열람실 좌석현황 -> 도서관 좌석 현황에 대해 알려드립니다\n";
		str += "(근조) 학사 공지사항 -> 최신 학사 공지(최대10개)를 알려드립니다\n";
		
		return str;
	}

	@Override
	public String libExecute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
