package com.royalevolution.royalcommands;

import org.bukkit.plugin.java.JavaPlugin;

public class RoyalCommands extends JavaPlugin {

	private static RoyalCommands instance;

	@Override
	public void onEnable() { // all necessary plugin startup functions go here

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

}
