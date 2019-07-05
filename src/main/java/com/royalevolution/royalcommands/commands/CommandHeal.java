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

					findAndHeal(sender, playerName);
				}

				return true;
			}

		} else if (args.length == 1) { // Console-sent command
			final String playerName = args[0];

			findAndHeal(commandSender, playerName);

			return true;
		}

		return true;
	}

	public static void findAndHeal(CommandSender sender, String playerName) {
		for (final Player player : RoyalCommands.getOnlinePlayers())
			if (player.getName().equals(playerName))
				if ((player.getHealth() == player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue())) {
					Common.tell(sender, RoyalCommands.getPrefix() + "&cThe target is already at full HP!");
					return;
				}
				else {
					Common.tell(player, RoyalCommands.getPrefix() + "You've been healed by " + "&3" + (sender instanceof Player ? sender.getName() : "Console") +"&r!");
					Common.tell(sender, RoyalCommands.getPrefix() + "You have successfully healed " + playerName + ".");
					player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					return;
				}

		Common.tell(sender, RoyalCommands.getPrefix() + "&cPlayer not found!");
	}


	//@Override
	//public List<String> tabComplete (CommandSender sender, String alias, String[] args) {
	//	return super.tabComplete(sender, alias, args); // this will simply tab-complete with someone's username, the default
	//}
}
