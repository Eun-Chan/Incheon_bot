package com.javaapp.incheon_Bot.command.notices;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class NoticesCommand implements ICommand {

	@Override
	public String execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		System.out.println("학사 공지사항");
		
		String url = "http://www.inu.ac.kr/user/boardList.do?boardId=48510";
		String result ="(치킨)인천대학교 공지사항(치킨)\n";
		int cnt = 0;
		
		try {
		Document doc = Jsoup.parse(new URL("http://www.inu.ac.kr/user/boardList.do?boardId=48510").openStream(), "UTF-8", "http://www.inu.ac.kr/user/boardList.do?boardId=48510");
		Elements elems = doc.select("div.tbList table tbody tr");
		for(Element elem : elems) {
			if(cnt == 10) break;
			result += elem.select("td.textAL a").html();
			result += "\n";
			cnt++;
		}
		
		} catch(Exception e) {}
		
		
		return result;
	}

	@Override
	public String libExecute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		return null;
	}

}
