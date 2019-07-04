package com.royalevolution.royalcommands;

import org.bukkit.plugin.java.JavaPlugin;

<<<<<<< HEAD
import com.royalevolution.royalcommands.commands.CommandBroadcast;
import com.royalevolution.royalcommands.commands.CommandFeed;
import com.royalevolution.royalcommands.commands.CommandFly;
import com.royalevolution.royalcommands.commands.CommandHat;
=======
>>>>>>> 1cea4c9804b2a806fcdfe39b53e1ee33acb1b328
import com.royalevolution.royalcommands.commands.CommandHeal;
import com.royalevolution.royalcommands.utils.Common;

public class RoyalCommands extends JavaPlugin {

	private final static String prefix = "&3&lR&b&loyal&3&lC&b&lommands &8> &r";

	private static RoyalCommands instance;

	@Override
	public void onEnable() { // all necessary plugin startup functions go here
<<<<<<< HEAD
		Common.registerCommand(new CommandFly());
		Common.registerCommand(new CommandFeed());
		Common.registerCommand(new CommandHeal());
		Common.registerCommand(new CommandBroadcast());
		Common.registerCommand(new CommandHat());
=======
		Common.registerCommand(new CommandHeal());
>>>>>>> 1cea4c9804b2a806fcdfe39b53e1ee33acb1b328
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
		return prefix;
	}

}



