package com.royalevolution.royalcommands;

import org.bukkit.plugin.java.JavaPlugin;

import com.royalevolution.royalcommands.commands.CommandFly;
import com.royalevolution.royalcommands.utils.Common;

public class RoyalCommands extends JavaPlugin {

	private static RoyalCommands instance;

	@Override
	public void onEnable() { // all necessary plugin startup functions go here
		Common.registerCommand(new CommandFly());
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public void reload() {
		// TODO: make it so this function updates all mutable data and call it upon an 'rc reload' command
	}

	public static RoyalCommands getInstance() {
		return instance;
	}
	public static String getPrefix() {
		String prefix = "&3&lR&b&loyal&3&lC&b&lommands &8> &r"; 
		return prefix;
	}

}
