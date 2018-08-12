package com.javaapp.incheon_Bot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
import com.javaapp.incheon_Bot.dto.KeyBoardDTO;
import com.javaapp.incheon_Bot.dto.MessageDTO;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;
import com.javaapp.incheon_Bot.dto.ResponseDTO;

import Command.FoodCommand;
import Command.ICommand;

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

		ArrayList<String> btn = new ArrayList<String>();
		
		btn.add("학식 메뉴");
		btn.add("처음으로");
		
		return new KeyBoardDTO(btn);
	}
	
	
	@RequestMapping("/message")
	public ResponseDTO message(@RequestBody RequestMessageDTO req) {
		System.out.println("message()");
		
		ResponseDTO res = new ResponseDTO();
		MessageDTO mes = new MessageDTO();
		
		if(req.getContent().equals("학식 메뉴")) {
			
			com = new FoodCommand();
			
			com.execute(req);
		}
		
		else if(req.getContent().equals("처음으로")) {
			
			ArrayList<String> btn = new ArrayList<String>();
			btn.add("학식 메뉴");
			btn.add("홈으로");
			
			res.setKeyboard(new KeyBoardDTO(btn));
			
			mes.setText("홈으로");
			
		}
		
		res.setMessage(mes);
		
		return res;
	}
}
