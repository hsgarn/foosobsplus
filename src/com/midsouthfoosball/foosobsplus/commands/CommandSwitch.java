package com.midsouthfoosball.foosobsplus.commands;

import java.util.HashMap;

public class CommandSwitch {
	public final HashMap<String, Command> commandMap = new HashMap<>();
	
	public void register(String commandName, Command command) {
		commandMap.put(commandName, command);
	}
	
	public Command execute(String commandName) {
		Command command = commandMap.get(commandName);
		if (command == null) {
			throw new IllegalStateException("no command registered for " + commandName);
		}
		command.execute();
		return command;
	}

}
