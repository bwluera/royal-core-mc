package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;

public class CommandHat extends PlayerCommand {

	public CommandHat() {
		super("hat");

		setAliases(Arrays.asList("rhat"));
		setDescription("Sets your hat to the item in your main hand.");
		setUsage("/hat [user]");

	}

	@Override
	protected void run(Player sender, String[] args) {
		if (sender.hasPermission("rc.hat")) {
			final ItemStack mainHand = sender.getInventory().getItemInMainHand();
			final ItemStack oldHat = sender.getInventory().getHelmet();
			if (mainHand != null && mainHand.getType() != Material.AIR) {
					Common.tell(sender, "Enjoy your new hat!");
					sender.getInventory().setHelmet(mainHand);
					sender.getInventory().setItemInMainHand(oldHat);
			} else 
				Common.tell(sender, RoyalCore.getChatPrefix() + "You can't wear nothing, silly");


		} else Common.sendNoPerm(sender);
	}

}

