package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;

import net.md_5.bungee.api.ChatColor;

public class CommandFeed extends PlayerCommand {

	public CommandFeed() {
		super("feed");

		setAliases(Arrays.asList("rfeed", "reat", "eat"));
		setDescription("feeds you or another player.");
		setUsage("/feed [user]");
	}

	@Override
	protected void run(Player sender, String[] args) {
		if (sender.hasPermission("rc.feed")) {
			if (args.length == 0) { //0 arguments means sender = target
				if (sender.getFoodLevel() != 20) {
					sender.setFoodLevel(20);
					Common.tell(sender, RoyalCore.getChatPrefix() + "You've been fed!");
				} else
					Common.tell(sender, RoyalCore.getChatPrefix() + "&cYou're not hungry!");
				return;
			}
			else if (args.length == 1) { //1 arguments means arg0 = target
				if (sender.hasPermission("rc.feed.others")) {
					final String targetName = args[0];
					for (final Player player : RoyalCore.getInstance().getServer().getOnlinePlayers())
						if (ChatColor.stripColor(player.getName()).equals(targetName))
							player.setFoodLevel(20);
						else
							Common.tell(sender, RoyalCore.getChatPrefix() + "&cPlayer not found!");
					if (sender.getFoodLevel() != 20) {
						sender.setFoodLevel(20);
						Common.tell(sender, RoyalCore.getChatPrefix() + "You've been fed!");
					} else
						Common.tell(sender, RoyalCore.getChatPrefix() + "&cYou're not hungry!");
				}
			} else
				Common.tell(sender, RoyalCore.getChatPrefix() + "&cSyntax error. Usage: &b/feed [player]");
		} else return;
	}
}