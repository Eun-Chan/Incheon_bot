package com.javaapp.incheon_Bot.library;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class LibraryCommand implements ICommand{

	@Override
	public String libExecute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		System.out.println("도서관 자리 현황()");
		
		String url = "";
		
		if(req.getContent().equals("자유열람실1"))
			url = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=1";
		else if(req.getContent().equals("자유열람실2"))
			url = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=2";
		else if(req.getContent().equals("자유열람실3"))
			url = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=3";
		else if(req.getContent().equals("노트북 코너"))
			url = "http://117.16.225.193:8080/seatmate/SeatMate.php?classInfo=4";
		
		String result ="";
		
		try {
		Document doc = Jsoup.connect(url).get();
			
		Elements elem1 = doc.select("div#location_box1 ul");
		
		// 열람실 이름
		result = "(외계인녀)";
		System.out.println(elem1.select("li.txt1").text());
		result += elem1.select("li.txt1").text();
		result += "(외계인녀)\n\n";
		
		// 총 좌석 수
		result += "총 좌석수 -> ";
		System.out.println(elem1.select("li.txt2").text());
		result += elem1.select("li.txt2").text();
		result += "\n";
		
		// 사용중인 좌석 수
		result += "사용중 -> ";
		System.out.println(elem1.select("li.txt3").text());
		result += elem1.select("li.txt3").text();
		result += "\n";
		
		// 남은 좌석 수
		result += "사용가능 -> ";
		System.out.println(elem1.select("li.txt4").text());
		result += elem1.select("li.txt4").text();
		
		} catch(Exception e) {}
		
		return result;
	}

	@Override
	public String execute(RequestMessageDTO req) {
		return null;
	}
	
}
