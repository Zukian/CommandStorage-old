package me.ale.commons.api.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandExecutor;

import me.ale.commons.bukkit.BukkitMain;

public class CommandStorage {
	
	private Map<String, CommandExecutor> commandMap;

	public CommandStorage() {
		commandMap = new HashMap<>();
	}
	
	/**
	 * Example
	 * @param commandExecutor
	 * 
	 * public void onEnable() {
	 * 		this.addNewCommand(new classNameCommand());
	 * 		this.loadCommands();
	 * }
	 */

	public void addNewCommand(CommandExecutor commandExecutor) {
		if (hasCommand(commandExecutor))
			return;
		String commandName = commandExecutor.getClass().getSimpleName().split("Command")[0];

		commandMap.put(commandName, commandExecutor);
	}

	public void loadCommands() {
		if(commandMap.isEmpty()) return;
		for (CommandExecutor commands : commandMap.values()) {
			String commandName = commands.getClass().getSimpleName().split("Command")[0];

			BukkitMain.getInstance().getCommand(commandName).setExecutor(commands);
		}
	}

	public CommandExecutor getCommand(CommandExecutor commandExecutor) {
		if (!hasCommand(commandExecutor))
			return null;
		String commandName = commandExecutor.getClass().getSimpleName().split("Command")[0];

		return commandMap.get(commandName);
	}

	public boolean hasCommand(CommandExecutor commandExecutor) {
		String commandName = commandExecutor.getClass().getSimpleName().split("Command")[0];
		return commandMap.containsKey(commandName);
	}
}
