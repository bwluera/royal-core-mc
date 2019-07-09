package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

import net.md_5.bungee.api.ChatColor;

public class CommandClearInv extends Command {

	public CommandClearInv() {
		super("clear");
		
		setAliases(Arrays.asList("clearinv", "invclear"));
		setDescription("Clears your or another player's inventory");
		setUsage("/clear [user]");

	}

	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Console's inventory can't get cleared!");
				return true;
			} 

			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);

				if (target == null) {
					Common.tell(sender, RoyalCommands.getChatPrefix() + "&cPlayer " + args[0] + " &ccould not be found.");
				}

				if (target != null) {
					Common.tell(sender, RoyalCommands.getChatPrefix() + "&3" + args[0] + "&f's inventory has been cleared");
					target.getInventory().clear();
				} 
			}
		}






		if (sender instanceof Player) {

			Player player = (Player)sender;



			if (args.length == 0) {

				player.getInventory().clear();
				Common.tell(sender, RoyalCommands.getChatPrefix() + "&fYour inventory has been cleared.");
			} 

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					if (player.hasPermission("rc.clear.all")) {

						Common.tell(sender, RoyalCommands.getChatPrefix() + "&fEveryones inventory has been cleared");

						for (Player all : Bukkit.getOnlinePlayers()) {

							all.getInventory().clear();

							Common.tell(all, RoyalCommands.getChatPrefix() + "&fYour inventory has been cleared by an admin");
						} 
					} 



					if (!player.hasPermission("rc.clear.all")) {
						
						Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou don't have permission to do this!");

						return true;
					} 
				} 


				if (!args[0].equalsIgnoreCase("all")) {

					Player target = Bukkit.getPlayer(args[0]);

					if (target == null) {
						Common.tell(sender, RoyalCommands.getChatPrefix() + "&cPlayer &4" + args[0] + " &ccould not be found.");
					}

					if (target != null) {
						if (player.hasPermission("rc.clear.others")) {
							target.getInventory().clear();
							Common.tell(sender, RoyalCommands.getChatPrefix() + "&3" + args[0] + "&f's inventory has been cleared");
							Common.tell(target, RoyalCommands.getChatPrefix() + "&fYour inventory has been cleared by an admin");
							return true;
						} 

						Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou don't have permission to do this!");
					} 
				} 
			} 
		} 

		return true;
	}

}