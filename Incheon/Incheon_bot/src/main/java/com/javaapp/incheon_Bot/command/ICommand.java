package com.javaapp.incheon_Bot.command;

import com.javaapp.incheon_Bot.dto.RequestMessageDTO;

public interface ICommand {

	public void execute(RequestMessageDTO req);
}
