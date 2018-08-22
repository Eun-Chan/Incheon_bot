package com.javaapp.incheon_Bot.command.food;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class FoodCommand implements ICommand{

	@Override
	public String execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		
		String url="";
		
		// "학생 1식당", "생활원 식당", "교직원 식당","카페테리아 식당","사범대 식당","처음으로"
		if(req.getContent().equals("학생 1식당"))
			url = "http://www.inu.ac.kr/com/cop/mainWork/foodList1.do?siteId=inu&id=inu_050110010000";
		else if(req.getContent().equals("생활원 식당"))
			url = "http://www.inu.ac.kr/com/cop/mainWork/foodList2.do?siteId=inu&id=inu_050110030000";
		else if(req.getContent().equals("교직원 식당"))
			url = "http://www.inu.ac.kr/com/cop/mainWork/foodList3.do?siteId=inu&id=inu_050110040000";
		else if(req.getContent().equals("카페테리아 식당"))
			url = "http://www.inu.ac.kr/com/cop/mainWork/foodList4.do?siteId=inu&id=inu_050110050000";
		else if(req.getContent().equals("사범대 식당"))
			url = "http://www.inu.ac.kr/com/cop/mainWork/foodList5.do?siteId=inu&id=inu_050110060000";
		
		String result = new String();
		
		try {
		Document doc = Jsoup.connect(url).get();
		
		//식당 이름
		result = req.getContent().toString();
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
			result += "\n";
		}
		
		} catch(Exception e) {}
		result += "======================";
		
		result = result.replace("메뉴명", "메뉴 : ");
		
		return result;
	}
	
	@Override
	public String libExecute(RequestMessageDTO req) {
		return null;
	}
}
