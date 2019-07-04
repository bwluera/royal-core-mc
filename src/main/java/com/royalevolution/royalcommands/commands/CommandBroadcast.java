package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;

public class CommandBroadcast extends PlayerCommand {

	public CommandBroadcast() {
		super("broadcast");
		
		setAliases(Arrays.asList("rbc", "bc", "rbroadcast"));
		setDescription("Broadcasts a message to the whole server");
		setUsage("/broadcast [message]");
	}

	@Override
	protected void run(Player sender, String[] args) {
		String message = "";
		for (String part : args) {
		    if (message != "") message += " ";
		    message += part;
		}
		for(Player all : RoyalCommands.getInstance().getServer().getOnlinePlayers()) {
			all.sendMessage("&3&lB&f&lroad&3&lc&f&last &8> &r" + message);
		}
	}
}