package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

public class CommandHeal extends Command {

	public CommandHeal() {
		super("heal"); // the command name

		setAliases(Arrays.asList("rheal"));
		setDescription("Heals you or another player.");
		setUsage("/heal [user]");
	}

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) { // TODO: make it so that you can heal for a specific amount?
		if (commandSender instanceof Player) {
			final Player sender = (Player) commandSender;
			Double defaultAmount = sender.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
			if (!sender.hasPermission("rc.heal")) { // no perms
				Common.tell(sender, "&4You do not have permission to use this command.");
				return true;
			}

			if (args.length == 0) {// sender targets their self
				if ((sender.getHealth() == sender.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue()))
					Common.tell(sender, RoyalCommands.getPrefix() + "&cYou're already at full HP!");
				else {
					Common.tell(sender, RoyalCommands.getPrefix() + "You've been healed.");
					sender.setHealth(sender.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
				}
				return true;
			}
			else if (args.length == 1) {
				if (!sender.hasPermission("rc.heal.others"))
					Common.tell(sender, "&4You do not have permission to use this command.");
				else {
					final String playerName = args[0];
					findAndHeal(sender, playerName, defaultAmount);
				}

				return true;
			} else if (args.length == 2) {
				final String playerName = args[0];
				
				try {
					
					final Double amount = Double.valueOf(args[1]);
					findAndHeal(sender, playerName, amount);
				} catch (Exception e) {
					//TODO: add real error message
					Common.tell(sender, "reee");
					}
				        
				}

		} else if (args.length == 1) { // Console-sent command
			final String playerName = args[0];
			//TODO: make console heal with the default value thing
			findAndHeal(commandSender, playerName, Double.valueOf(20));

			return true;
		}

		return true;
	}


	private static Double setAmount(Player player, Double amount) {
		if (amount == player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue()) {
			return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
		} else {
			if ((player.getHealth() + amount) > 20) {
				return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
			} else return player.getHealth() + amount;
			
		}
		
	}
	
	public static void findAndHeal(CommandSender sender, String playerName, Double amount) {
		for (final Player player : RoyalCommands.getOnlinePlayers())
			if (player.getName().equals(playerName))
				if ((player.getHealth() == player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue())) {
					Common.tell(sender, RoyalCommands.getPrefix() + "&cThe target is already at full HP!");
					return;
				}
				else {
					if (amount.equals(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue())) {
					Common.tell(player, RoyalCommands.getPrefix() + "You've been healed by &3" + (sender instanceof Player ? sender.getName() : "Console") +"&r!");
					} else {
						Common.tell(player, RoyalCommands.getPrefix() + "You've been healed by &3" + (sender instanceof Player ? sender.getName() : "Console") 
								+ "&rfor &3" + amount + "&bHP &r!");
					}
					if (amount.equals(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue())) {
						Common.tell(sender, RoyalCommands.getPrefix() + "You have successfully healed &3" + playerName + "&r.");
						} else {
							Common.tell(sender, RoyalCommands.getPrefix() + "You have successfully healed &3" + playerName + "&rfor &3" + amount + "&bHP &r!");
						}
					
					if (amount.equals(null)) amount = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
					player.setHealth(setAmount(player, amount));
					return;
				}

		Common.tell(sender, RoyalCommands.getPrefix() + "&cPlayer not found!");
	}


	//@Override
	//public List<String> tabComplete (CommandSender sender, String alias, String[] args) {
	//	return super.tabComplete(sender, alias, args); // this will simply tab-complete with someone's username, the default
	//}
}
