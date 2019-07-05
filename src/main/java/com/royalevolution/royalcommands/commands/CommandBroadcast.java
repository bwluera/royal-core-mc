package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

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
		for (final String part : args) {
			if (message != "") message += " ";
			message += part;
		}
		for (final Player player : RoyalCommands.getOnlinePlayers())
			player.sendMessage(Common.colorize("&3&lB&f&lroad&3&lc&f&last &8> &r" + message));
	}
}