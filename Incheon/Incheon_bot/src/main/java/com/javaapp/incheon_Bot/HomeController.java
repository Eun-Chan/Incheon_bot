package com.javaapp.incheon_Bot;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.command.food.FoodCommand1;
import com.javaapp.incheon_Bot.command.food.FoodCommand2;
import com.javaapp.incheon_Bot.command.food.FoodCommand3;
import com.javaapp.incheon_Bot.command.food.FoodCommand4;
import com.javaapp.incheon_Bot.command.food.FoodCommand5;
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
	
		return new KeyBoardDTO(new String[] {"학식 메뉴", "송도 날씨","테스트5"});
	}
	
	
	@RequestMapping("/message")
	public ResponseMessageDTO message(@RequestBody RequestMessageDTO req) {
		System.out.println("message()");
		
		ResponseMessageDTO res = new ResponseMessageDTO();
		MessageDTO mes = new MessageDTO();
		
		if(req.getContent().equals("학식 메뉴")) {
			
			//HomeController home = new HomeController();
			//home.sickDang_init();
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());	
			res.setKeyboard(keyboard);
			mes.setText("학식 메뉴()");
		}
		
		else if(req.getContent().equals("처음으로")) {
			
			String[] btn = {"학식 메뉴","송도 날씨"};
			res.setKeyboard(new KeyBoardDTO(btn));
			
			mes.setText("홈으로");
		}
		
		else if(req.getContent().equals("학생 1식당")) {
			
			// 학생 1식당 크롤링
			com = new FoodCommand1();
			
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());
			res.setKeyboard(keyboard);
			mes.setText(com.execute());
		}
		
		else if(req.getContent().equals("생활원 식당")) {
			
			// 생활원식당 크롤링
			com = new FoodCommand2();
			
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());	
			res.setKeyboard(keyboard);
			mes.setText(com.execute());
		}
		
		else if(req.getContent().equals("교직원 식당")) {
			
			// 교직원식당 크롤링
			com = new FoodCommand3();
			
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());
			res.setKeyboard(keyboard);
			mes.setText(com.execute());
		}
		
		else if(req.getContent().equals("카페테리아 식당")) {
			
			//카페테리아 식당 크롤링
			com = new FoodCommand4();
			
			// keyboard 초기회 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());	
			res.setKeyboard(keyboard);
			mes.setText(com.execute());
		}
		
		else if(req.getContent().equals("사범대 식당")) {
			
			// 사범대 식당 크롤링
			com = new FoodCommand5();
			
			// keyboard 초기회 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(sickDang_init());
			res.setKeyboard(keyboard);
			mes.setText(com.execute());
		}
	
		else if(req.getContent().equals("송도 날씨")) {
			
			// 날씨 API
			
		}
		res.setMessage(mes);
		return res;
	}
	
	public String[] sickDang_init() {
		
		String[] btn = {"학생 1식당", "생활원 식당", "교직원 식당","카페테리아 식당","사범대 식당","처음으로"};
		return btn;
	}
}
