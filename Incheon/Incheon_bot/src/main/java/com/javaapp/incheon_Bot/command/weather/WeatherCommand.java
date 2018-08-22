package com.javaapp.incheon_Bot.command.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.javaapp.incheon_Bot.command.ICommand;
import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public class WeatherCommand implements ICommand{

	private static final String openWeatherApiURL="http://api.openweathermap.org/data/2.5/weather?q=Incheon&appid=a4eeb63fbf7acfd0eecdf31ba747d883";
	
	@Override
	public String execute(RequestMessageDTO req) {
		// TODO Auto-generated method stub
		
		String weatherStr = "";
		
		try {
			URL url = new URL(openWeatherApiURL);
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line;
			String result="";
			
			//버퍼에 있는 정보를 문자열로 변환.
            while((line=bf.readLine())!=null){
                result=result.concat(line);
                //System.out.println(line);
            }
			
			//System.out.println(result);
			
			//문자열을 JSON으로 파싱
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObj = (JsonObject) jsonParser.parse(result);
			
            // 현재 날짜 넣기
            Date date = new Date();
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            weatherStr = "======================\n";
            weatherStr += time.format(date);            
            
            // 지역 출력
            weatherStr += jsonObj.get("name");
            weatherStr = weatherStr.replace("\"Incheon\"", " 인천 날씨");
            weatherStr += "\n";
           
            // 날씨 출력 (ex) Clear 맑음 , Snow (눈) , Clouds (흐림) , Rain (비) , Atmosphere (기압) , Drizzle(보슬비) , ThunderStorm(천둥군주))
            JsonArray weatherObj = (JsonArray) jsonObj.get("weather");
            JsonObject obj = (JsonObject) weatherObj.get(0);
            
            weatherStr += obj.get("main");
            weatherStr = weatherStr.replace(obj.get("main").toString(), transWeather(obj.get("main").toString()));
            weatherStr += "\n";
            
            // 온도출력 (절대 ==> 섭씨 변환)
            JsonObject tempObj = (JsonObject) jsonObj.get("main");
            double temp = Double.parseDouble(tempObj.get("temp").toString());
            temp-=273.15;
            
            System.out.println((int)Math.rint(temp));
            weatherStr += (int)Math.rint(temp);
            weatherStr += "℃";
            weatherStr += "\n======================\n";
			} catch(Exception e) {}
		
		return weatherStr;
	}
	
	@Override
	public String libExecute(RequestMessageDTO req) {
		return null;
	}
	
	public String transWeather(String str) {

		// 날씨 출력 (ex) Clear 맑음 , Snow (눈) , Clouds (흐림) , Rain (비) , Atmosphere (기압) , Drizzle(보슬비) , ThunderStorm(천둥군주))
		if(str.equals("\"Clear\"")) 
			return "맑음";
		
		else if(str.equals("\"Snow\""))
			return "눈";
		
		else if(str.equals("\"Clouds\""))
			return "흐림";
	
		else if(str.equals("\"Rain\""))
			return "비";
		
		else if(str.equals("\"Atmosphere\""))
			return "기압";
		
		else if(str.equals("\"Drizzle\""))
			return "보슬비";
		
		else if(str.equals("\"ThunderStorm\""))
			return "천둥군주";
		
		return null;
	}
	

}
