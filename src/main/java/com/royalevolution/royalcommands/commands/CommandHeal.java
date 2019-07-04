package com.royalevolution.royalcommands.commands;

import java.util.Collection;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.royalevolution.royalcommands.RoyalCommands;

public class CommandHeal extends PlayerCommand {

	public CommandHeal() {
		super("heal"); // the command name
	}

	@Override
	protected void run(Player sender, String[] args) {
		if (!sender.hasPermission("rc.heal"))
			return;
		else if (args.length == 0) // sender targets their self
			sender.setHealth(sender.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
		else if (args.length == 1) { // sender targets another player
			final String playerName = args[0];

			final Collection<? extends Player> onlinePlayers = RoyalCommands.getInstance().getServer().getOnlinePlayers();

			for (final Player player : onlinePlayers)
				if (player.getName().equals(playerName)) {
					player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					break;
				}

		}

	}


	//@Override
	//public List<String> tabComplete (CommandSender sender, String alias, String[] args) {
	//	return super.tabComplete(sender, alias, args); // this will simply tab-complete with someone's username, the default
	//}

}
