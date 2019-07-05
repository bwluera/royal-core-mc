package com.royalevolution.royalcommands.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.royalevolution.royalcommands.RoyalCommands;
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
			final Integer helmSlot = 103;
			final ItemStack mainHand = sender.getInventory().getItemInMainHand();
			final ItemStack oldHat = sender.getInventory().getItem(helmSlot);
			if (mainHand != null) {
				if (Common.invCanFit(sender.getInventory())) {
					sender.getInventory().setItem(helmSlot, mainHand);
					sender.getInventory().setItemInMainHand(oldHat);
				} else 
					Common.tell(sender, RoyalCommands.getPrefix() + "&cYou don't have any space in your inventory!");
			} else 
				Common.tell(sender, RoyalCommands.getPrefix() + "You can't wear nothing, silly");


		} else Common.sendNoPerm(sender);
	}

}

