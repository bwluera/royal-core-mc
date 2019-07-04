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
			Integer helmSlot = sender.getInventory().getHeldItemSlot();
			ItemStack hat = sender.getInventory().getItemInMainHand();
			ItemStack oldHat = sender.getInventory().getItem(helmSlot);
			if (hat != null && sender.getInventory().getContents().length < 36) {
				sender.getInventory().setItem(helmSlot, hat);
				sender.getInventory().setItemInMainHand(oldHat);
				
				sender.getInventory().setItem(helmSlot, hat);
			} else Common.tell(sender, RoyalCommands.getPrefix() + "You can't wear nothing, silly");
				
				
				
		} else return; //no perms
		
	}


}