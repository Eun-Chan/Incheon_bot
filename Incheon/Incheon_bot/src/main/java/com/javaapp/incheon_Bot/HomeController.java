package com.javaapp.incheon_Bot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.command.food.FoodCommand1;
import com.javaapp.incheon_Bot.dto.KeyBoardDTO;
import com.javaapp.incheon_Bot.dto.MessageDTO;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;
import com.javaapp.incheon_Bot.dto.ResponseMessageDTO;


/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	ICommand com;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/keyboard")
	public KeyBoardDTO keybard() {
		System.out.println("keyboard()");
		
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
		
		return new KeyBoardDTO(new String[] {"학식 메뉴" , "처음으로"});
	}
	
	
	@RequestMapping("/message")
	public ResponseMessageDTO message(@RequestBody RequestMessageDTO req) {
		System.out.println("message()");
		
		ResponseMessageDTO res = new ResponseMessageDTO();
		MessageDTO mes = new MessageDTO();
		
		if(req.getContent().equals("학식 메뉴")) {
			
			com = new FoodCommand1();
			
			com.execute(req);
		}
		
		else if(req.getContent().equals("처음으로")) {
			
			String[] btn = {"학식 메뉴","처음으로"};
			res.setKeyboard(new KeyBoardDTO(btn));
			
			mes.setText("홈으로");
			
		}
		
		res.setMessage(mes);
		
		return res;
	}
}
