package com.javaapp.incheon_Bot.command.food;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class FoodCommand2 implements ICommand {

	@Override
	public void execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		
		System.out.println("생활원 식당 command.execute()");
		
		String url = "http://www.inu.ac.kr/com/cop/mainWork/foodList2.do?siteId=inu&id=inu_050110030000";
		
		try {
		Document doc = Jsoup.connect(url).get();
		
		//식당 이름
		Element elem1 = doc.select("ul.subTab li a").get(0);
		String resName = elem1.select("a").html();
		
		//식당 코너
		Elements elem2 = doc.select("div.sickdangmenu dl");
		
		System.out.println("식당 이름 : " +resName);
		
		for(Element elem : elem2) {
			System.out.println(elem.select("dt").text());
			System.out.println(elem.select("dd").text());
		}
		
		} catch(Exception e) {}
		
	}

}
