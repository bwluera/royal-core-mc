package com.royalevolution.royalcommands;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.royalevolution.royalcommands.commands.CommandBroadcast;
import com.royalevolution.royalcommands.commands.CommandClearInv;
import com.royalevolution.royalcommands.commands.CommandFeed;
import com.royalevolution.royalcommands.commands.CommandFly;
import com.royalevolution.royalcommands.commands.CommandHat;
import com.royalevolution.royalcommands.commands.CommandHeal;
import com.royalevolution.royalcommands.events.JoinLeaveListener;
import com.royalevolution.royalcommands.utils.Common;

public class RoyalCommands extends JavaPlugin {

	private final static String prefix = "&3&lR&b&loyal&3&lC&b&lommands &8> &r";

	private static RoyalCommands instance;

	@Override
	public void onEnable() { // all necessary plugin startup functions go here

		instance = this;

		Common.registerCommands(
				new CommandHat(),
				new CommandFly(),
				new CommandFeed(),
				new CommandHeal(),
				new CommandClearInv(),
				new CommandBroadcast()
				);
		Common.registerEvents(this, 
				new JoinLeaveListener());

		loadConfigManager();
	}

	@Override
	public void onDisable() {
		instance = null;
	}
	
	private ConfigManager confm;
	
	public void loadConfigManager() {
		confm = new ConfigManager();
		confm.filesSetup();
		confm.saveFiles();
		confm.reloadFiles();
	}
	public void reload() {
		// TODO: make it so this function updates all mutable data and call it upon an 'rc reload' command
	}

	public static RoyalCommands getInstance() {
		return instance;
	}
	
	public static Plugin getPlugin() {
		return RoyalCommands.getPlugin(RoyalCommands.class);
	}

	public static String getPrefix() {
		return prefix;
	}

	public static Collection<? extends Player> getOnlinePlayers() {
		return instance.getServer().getOnlinePlayers();
	}


}