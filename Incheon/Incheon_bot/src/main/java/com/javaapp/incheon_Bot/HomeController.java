package com.javaapp.incheon_Bot;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.command.food.FoodCommand;
import com.javaapp.incheon_Bot.command.help.HelpCommand;
import com.javaapp.incheon_Bot.command.notices.NoticesCommand;
import com.javaapp.incheon_Bot.command.weather.WeatherCommand;
import com.javaapp.incheon_Bot.dao.IDao;
import com.javaapp.incheon_Bot.dto.KeyBoardDTO;
import com.javaapp.incheon_Bot.dto.MessageButtonDTO;
import com.javaapp.incheon_Bot.dto.MessageDTO;
import com.javaapp.incheon_Bot.dto.NoticeDTO;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;
import com.javaapp.incheon_Bot.dto.ResponseMessageDTO;
import com.javaapp.incheon_Bot.dto.WeatherDTO;
import com.javaapp.incheon_Bot.library.LibraryCommand;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	// Command 패턴
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
		
		return new KeyBoardDTO(btn_init(0));
	}
	
	
	@RequestMapping("/message")
	public ResponseMessageDTO message(@RequestBody RequestMessageDTO req) {
		System.out.println("message()");
		
		ResponseMessageDTO res = new ResponseMessageDTO();
		MessageDTO mes = new MessageDTO();
		
		if(req.getContent().equals("학식 메뉴")) {
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(btn_init(1));	
			res.setKeyboard(keyboard);
			mes.setText("학식 메뉴()");
		}
		
		else if(req.getContent().equals("처음으로")) {
			res.setKeyboard(new KeyBoardDTO(btn_init(0)));
			mes.setText("홈으로");
		}
		
		else if(req.getContent().equals("학생 1식당") || req.getContent().equals("생활원 식당") || req.getContent().equals("교직원 식당") || req.getContent().equals("카페테리아 식당") || req.getContent().equals("사범대 식당")) {
			
			// 학생 1식당 크롤링
			com = new FoodCommand();
			
			// keyboard 초기화 (식당)
			KeyBoardDTO keyboard = new KeyBoardDTO(btn_init(1));
			res.setKeyboard(keyboard);
			mes.setText(com.execute(req));
		}
			
		else if(req.getContent().equals("인천 날씨")) {
			
			// openWeatherAPI
			com = new WeatherCommand();
			
			res.setKeyboard(new KeyBoardDTO(btn_init(0)));
			mes.setText(com.execute(req));
		}
		
		else if(req.getContent().equals("인천대 입구역 하철이 도착시간")) {
			
			// 지하철API
			
		}
		
		else if(req.getContent().equals("열람실 좌석현황")) {
			
			res.setKeyboard(new KeyBoardDTO(btn_init(2)));
			mes.setText("열람실을 선택하세요!");
		}
		
		else if(req.getContent().equals("자유열람실1") || req.getContent().equals("자유열람실2") || req.getContent().equals("자유열람실3") || req.getContent().equals("노트북 코너")){
		
			// 이미지 버튼 url
			String imgURL ="";
			
			// 도서관 좌석 크롤링 실행
			com = new LibraryCommand();
			res.setKeyboard(new KeyBoardDTO(btn_init(2)));
			mes.setText(com.libExecute(req));
					
			// 도서관 좌석 메세지 버튼 
			if(req.getContent().equals("자유열람실1"))
				imgURL = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=1";
			else if(req.getContent().equals("자유열람실2"))
				imgURL = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=2";
			else if(req.getContent().equals("자유열람실3"))
				imgURL = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=3";
			else if(req.getContent().equals("노트북 코너"))
				imgURL = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=4";
			
			MessageButtonDTO mesBtn = new MessageButtonDTO();
			mesBtn.setUrl(imgURL);
			mesBtn.setLabel(req.getContent());
			mes.setMessage_button(mesBtn);	
		}
	
		else if(req.getContent().equals("학사 공지사항")) {
			
			res.setKeyboard(new KeyBoardDTO(btn_init(3)));
			mes.setText(req.getContent());
		}
		
		// "학사","학점교류","일반","장학금","모집채용"
		else if(req.getContent().equals("학사") || req.getContent().equals("학점교류") || req.getContent().equals("일반") || req.getContent().equals("장학금") || req.getContent().equals("모집채용")) {
			
			// 공지사항 메세지 버튼 (홈페이지로 이동)
			String imgURL = "";
			
			if(req.getContent().equals("학사"))
				imgURL = "http://www.inu.ac.kr/user/boardList.do?boardId=49211&siteId=inu&id=inu_070202000000";
			else if(req.getContent().equals("학점 교류"))
				imgURL = "http://www.inu.ac.kr/user/boardList.do?boardId=197438&siteId=inu&id=inu_070211000000";
			else if(req.getContent().equals("일반"))
				imgURL = "http://www.inu.ac.kr/user/boardList.do?boardId=49219&siteId=inu&id=inu_070203000000";
			else if(req.getContent().equals("장학금"))
				imgURL = "http://www.inu.ac.kr/user/boardList.do?boardId=49227&siteId=inu&id=inu_070204000000";
			else if(req.getContent().equals("모집채용"))
				imgURL = "http://www.inu.ac.kr/user/boardList.do?boardId=49235&siteId=inu&id=inu_070205000000";
			
			MessageButtonDTO mesBtn = new MessageButtonDTO();
			mesBtn.setUrl(imgURL);
			mesBtn.setLabel(req.getContent());
			mes.setMessage_button(mesBtn);
			
			// 공지사항 commnad
			com = new NoticesCommand();
			res.setKeyboard(new KeyBoardDTO(btn_init(3)));
			mes.setText(com.execute(req));
		}
		
		else if(req.getContent().equals("사용법")) {
			
			// 사용법 Command
			com = new HelpCommand();
			
			res.setKeyboard(new KeyBoardDTO(btn_init(0)));
			mes.setText(com.execute(req));
		}
		
		res.setMessage(mes);
		return res;
		
	}
		
	public String[] btn_init(int idx) {
		
		// idx = 0 , 홈 btn
		if(idx == 0) {
			String[] btn = {"학식 메뉴", "인천 날씨","열람실 좌석현황","학사 공지사항","사용법"};
			return btn;
		}
		// idx = 1 , 식당 btn
		else if(idx == 1) {
			String[] btn = {"학생 1식당", "생활원 식당", "교직원 식당","카페테리아 식당","사범대 식당","처음으로"};
			return btn;
		}
		// idx = 2 , 학교 도서관 좌석 btn
		else if(idx == 2) {
			String[] btn = {"자유열람실1","자유열람실2","자유열람실3","노트북 코너","처음으로"};
			return btn;
		}
		
		// idx = 3 , 공지사항 btn
		else if(idx == 3) {
			String[] btn = {"학사","학점교류","일반","장학금","모집채용","처음으로"};
			return btn;
		}
	
		return null;
	}	
}
