package com.royalevolution.royalcommands;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.royalevolution.royalcommands.utils.Common;

public class CustomRecipes {
	public static void addCustomRecipes() {
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("test");
		helmet.setItemMeta(helmetMeta);
						//recipe
		Common.addRecipe(Material.EMERALD, Material.EMERALD, Material.EMERALD, 
				         Material.EMERALD, Material.AIR, Material.EMERALD, 
				         Material.AIR, Material.AIR, Material.AIR, 
				         //outcome
				         helmet);
	}
}