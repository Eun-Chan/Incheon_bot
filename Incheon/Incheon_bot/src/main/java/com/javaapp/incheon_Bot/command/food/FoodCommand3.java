package com.javaapp.incheon_Bot.command.food;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class FoodCommand3 implements ICommand {

	@Override
	public String execute() {
		// TODO Auto-generated method stub

		System.out.println("교직원 식당 command.execute()");
		
		String url = "http://www.inu.ac.kr/com/cop/mainWork/foodList3.do?siteId=inu&id=inu_050110040000";
		
		String result = new String();
		
		try {
		Document doc = Jsoup.connect(url).get();
		
		//식당 이름
		Element elem1 = doc.select("ul.subTab li a").get(2);
		result = elem1.select("a").html();
		result += "\n======================\n";
		//식당 코너
		Elements elem2 = doc.select("div.sickdangmenu dl");
		
		//System.out.println("식당 이름 : " +resName);
		
		for(Element elem : elem2) {
			System.out.println(elem.select("dt").text());
			result += elem.select("dt").text();
			result += "\n";
			System.out.println(elem.select("dd").text());
			result += elem.select("dd").text();
			result += "\n\n";
		}
		
		} catch(Exception e) {}
		result += "======================";
		return result;
	}

}
