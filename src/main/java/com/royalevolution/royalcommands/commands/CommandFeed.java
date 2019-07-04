package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
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
					Common.tell(sender, RoyalCommands.getPrefix() + "You've been fed!");
				} else {
					Common.tell(sender, RoyalCommands.getPrefix() + "&cYou're not hungry!");
				}	
			} else if
			(args.length == 1) { //1 arguments means arg0 = target
				if (sender.hasPermission("rc.feed.others")) {
					String targetName = args[0];
					for (Player all : RoyalCommands.getInstance().getServer().getOnlinePlayers()) {
						if (ChatColor.stripColor(all.getDisplayName()).equals(targetName)) {
							all.setFoodLevel(20);
						} else {
							Common.tell(sender, RoyalCommands.getPrefix() + "&cPlayer not found!");
						}
					}
					if (sender.getFoodLevel() != 20) {
						sender.setFoodLevel(20);
						Common.tell(sender, RoyalCommands.getPrefix() + "You've been fed!");
					} else {
						Common.tell(sender, RoyalCommands.getPrefix() + "&cYou're not hungry!");
					}
				}
			} else {
				Common.tell(sender, RoyalCommands.getPrefix() + "&cSyntax error. Usage: &b/feed [player]");
			}
		} else return;
	}
}