package com.royalevolution.royalcommands.commands;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;

public class CommandHeal extends PlayerCommand {

	public CommandHeal() {
		super("heal"); // the command name

		setAliases(Arrays.asList("rheal"));
		setDescription("Heals you or another player.");
		setUsage("/heal [user]");
	}

	@Override
	protected void run(Player sender, String[] args) {
		if (!sender.hasPermission("rc.heal")) {
			Common.tell(sender, "&4You do not have permission to use this command.");
			return;
		}

		if (args.length == 0) // sender targets their self
			sender.setHealth(sender.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
		else if (args.length == 1) { // sender targets another player
			if (sender.hasPermission("rc.heal.others")) {

			final String playerName = args[0];

			final Collection<? extends Player> onlinePlayers = RoyalCommands.getInstance().getServer().getOnlinePlayers();

			for (final Player player : onlinePlayers)
				if (player.getName().equals(playerName)) {
					player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					break;
				}
			} else {
				return;
				//no perm stuff
			}

		}

	}


	//@Override
	//public List<String> tabComplete (CommandSender sender, String alias, String[] args) {
	//	return super.tabComplete(sender, alias, args); // this will simply tab-complete with someone's username, the default
	//}

}