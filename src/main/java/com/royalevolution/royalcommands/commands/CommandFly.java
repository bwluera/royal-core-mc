package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

import net.md_5.bungee.api.ChatColor;

public class CommandFly extends PlayerCommand {

	public CommandFly() {
		super("fly");
		
		setAliases(Arrays.asList("rfly"));
		setDescription("Enables flight for you or another player.");
		setUsage("/fly [user]");
	}

	@Override
	protected void run(Player sender, String[] args) {
		String prefix = RoyalCommands.getPrefix();
		if (sender.hasPermission("rc.fly")) {
			if (args.length == 0) { //no args. sender = target
				if (!sender.getAllowFlight()) {
					sender.setAllowFlight(true);
					Common.tell(sender, prefix + "Your flight has been &aenabled");
				} else {
					sender.setAllowFlight(false);
					Common.tell(sender, prefix + "Your flight has been &cdisabled");
				}
			} else if (args.length == 1) { //arg arg0 = target
				if (sender.hasPermission("rc.fly.others")) {
				String targetName = args[0];
				for (Player all : RoyalCommands.getInstance().getServer().getOnlinePlayers()) {
					if (ChatColor.stripColor(all.getDisplayName()).equals(targetName)) {
						if (!all.getAllowFlight()) {
							all.setAllowFlight(true);
							Common.tell(all, prefix + "Your flight has been &aenabled &rby &b" + sender.getDisplayName());
						} else {
							all.setAllowFlight(false);
							Common.tell(all, prefix + "Your flight has been &cdisabled &rby &b" + sender.getDisplayName());
						}
					} else {
						//player not found
						Common.tell(sender, prefix + "&cPlayer not found!");
					}
					
				}
			} else return; //no permission message for flying others
		} else {
			//syntax error
		}
	} else return; //no permission for using /fly
}
}
