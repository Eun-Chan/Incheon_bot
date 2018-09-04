package com.javaapp.incheon_Bot.dao;

import java.util.ArrayList;

import com.javaapp.incheon_Bot.dto.NoticeDTO;

public interface IDao {

	public ArrayList<NoticeDTO> listDao();
	public void writeDao(String mContent , int mhits);
}
