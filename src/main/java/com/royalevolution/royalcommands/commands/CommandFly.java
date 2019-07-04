package com.royalevolution.royalcommands.commands;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

public class CommandFly extends PlayerCommand {

	public CommandFly() {
		super("fly");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void run(Player sender, String[] args) {
		if (!sender.getAllowFlight()) {
			sender.setAllowFlight(true);
			Common.tell(sender, RoyalCommands.getPrefix() + "Your flight has been &aenabled");
		} else {
			sender.setAllowFlight(false);
			Common.tell(sender, "Your flight has been &cdisabled");
		}
	}

}
