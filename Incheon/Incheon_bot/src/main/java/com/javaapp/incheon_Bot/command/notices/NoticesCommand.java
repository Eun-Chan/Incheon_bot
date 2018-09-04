package com.javaapp.incheon_Bot.command.notices;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dao.IDao;
import com.javaapp.incheon_Bot.dto.NoticeDTO;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class NoticesCommand implements ICommand {

	@Override
	public String execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		System.out.println("학사 공지사항");
		
		String url = "";
		
		// "학사","학점교류","일반","장학금","모집채용"
		if(req.getContent().equals("학사"))
			url = "http://www.inu.ac.kr/rssList.jsp?siteId=inu&boardId=49211";
		else if(req.getContent().equals("학점교류"))
			url = "http://www.inu.ac.kr/rssList.jsp?siteId=inu&boardId=197438";
		else if(req.getContent().equals("일반"))
			url = "http://www.inu.ac.kr/rssList.jsp?siteId=inu&boardId=49219";
		else if(req.getContent().equals("장학금"))
			url = "http://www.inu.ac.kr/rssList.jsp?siteId=inu&boardId=49227";
		else if(req.getContent().equals("모집채용"))
			url = "http://www.inu.ac.kr/rssList.jsp?siteId=inu&boardId=49235";
		
		String result ="(치킨)인천대학교 공지사항";
		result += " - " + req.getContent() +"(치킨)\n";
		
		try {
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = f.newDocumentBuilder();
			
			Document xmlDoc = null;
			xmlDoc = parser.parse(url);
			
			Element root = xmlDoc.getDocumentElement();
			//System.out.println(root.getTagName());
			
			for(int i = 0 ; i < 10;i ++) {
				Node xmlNode = root.getElementsByTagName("item").item(i);
				
				Node xmlTitle = ((Element)xmlNode).getElementsByTagName("title").item(0);
				System.out.println(xmlTitle.getTextContent());
				result+=xmlTitle.getTextContent();
				result+="\n";
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
