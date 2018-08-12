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
import com.javaapp.incheon_Bot.dto.keyboardDTO;
import com.javaapp.incheon_Bot.dto.messageDTO;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
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
	public keyboardDTO keybard() {
		System.out.println("keyboard()");

		ArrayList<String> btn = new ArrayList<String>();
		
		btn.add("학식 메뉴");
		
		return new keyboardDTO(btn);
	}
	
	
	@RequestMapping("/message")
	public messageDTO message(@RequestBody messageDTO msgDTO) {
		System.out.println("message()");
		
		messageDTO msg = new messageDTO();
		
		
		
		return msg;
	}
}
