package com.javaapp.incheon_Bot.command;

import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public interface ICommand {

	public String execute(RequestMessageDTO req);
	public String libExecute(RequestMessageDTO req);
}
