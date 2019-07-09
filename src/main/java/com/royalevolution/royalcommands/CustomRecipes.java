package com.royalevolution.royalcommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class CustomRecipes {
	public static void addCustomRecipes() {
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("test");
		helmet.setItemMeta(helmetMeta);
		//recipe
		addRecipe(Material.EMERALD, Material.EMERALD, Material.EMERALD
				, Material.EMERALD, Material.DIAMOND, Material.EMERALD
				, Material.EMERALD, Material.EMERALD, Material.EMERALD
				, helmet);
	}

	private static void addRecipe (Material m1, Material m2, Material m3, 
								   Material m4, Material m5, Material m6,
								   Material m7, Material m8, Material m9, ItemStack outcome) {
		NamespacedKey key = new NamespacedKey(RoyalCommands.getPlugin(), RoyalCommands.getPlugin().getDescription().getName());
		ShapedRecipe recipe = new ShapedRecipe(key, outcome);
		recipe.shape("abc","def","ghi");
		recipe.setIngredient('a', m1);
		recipe.setIngredient('b', m2);
		recipe.setIngredient('c', m3);
		recipe.setIngredient('d', m4);
		recipe.setIngredient('e', m4);
		recipe.setIngredient('f', m6);
		recipe.setIngredient('g', m7);
		recipe.setIngredient('h', m8);
		recipe.setIngredient('i', m9);
		Bukkit.getServer().addRecipe(recipe);

	}
}