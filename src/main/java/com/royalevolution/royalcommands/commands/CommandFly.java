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
		final String prefix = RoyalCommands.getPrefix();

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
					final String targetName = args[0];
					for (final Player player : RoyalCommands.getOnlinePlayers())
						if (ChatColor.stripColor(player.getDisplayName()).equals(targetName)) {
							if (!player.getAllowFlight()) {
								player.setAllowFlight(true);
								Common.tell(player, prefix + "Your flight has been &aenabled &rby &b" + sender.getName());
							} else {
								player.setAllowFlight(false);
								Common.tell(player, prefix + "Your flight has been &cdisabled &rby &b" + sender.getName());
							}
						} else
							//player not found
							Common.tell(sender, prefix + "&cPlayer not found!");
				} else return; // no permission message for flying others
			} else {
				Common.tell(sender, prefix + "&cSyntax error. Usage: &b/fly [player]");
				//syntax error
			}
		} else return; // no permission for using /fly
	}
}
