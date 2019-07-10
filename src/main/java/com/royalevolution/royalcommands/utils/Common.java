package com.royalevolution.royalcommands.utils;


import java.lang.reflect.Field;
import java.util.*;

import com.royalevolution.royalcommands.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.PluginManager;

import com.royalevolution.royalcommands.RoyalCore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Common {

	public static void sendActionBar(Player player, String title) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(colorize(title)));
	}

	public static void tell(CommandSender sender, String message) {
		sender.sendMessage(colorize(message));
	}


	public static boolean invCanFit(Inventory inv) {
		return inv.getContents().length < 36;
	}

	public static void tell(CommandSender sender, String... messages) {
		for (final String message : messages)
			sender.sendMessage(colorize(message));
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void log(String message) {
		tell(Bukkit.getConsoleSender(), "[" + RoyalCore.getInstance().getName() + "] " + message);
	}

	public static void registerEvents(RoyalCore plugin, Listener... listeners) {
		final PluginManager pm = plugin.getServer().getPluginManager();

		for (final Listener lis : listeners)
			pm.registerEvents(lis, plugin);
	}

	public static void registerCommand(Command command) {
		try {
			final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			commandMapField.setAccessible(true);

			final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
			commandMap.register(command.getLabel(), command);
		} catch (final Exception e) {
			e.printStackTrace();

			log("&4Could not register command: " + command.getName());
		}
	}

	public static void sendNoPerm(CommandSender sender) {
		Common.tell(sender, "&4You do not have permission to use this command.");
	}

	public static void registerCommands(Command...commands) {
		for (final Command command : commands)
			registerCommand(command);
	}

	public static ArrayList<String> getPermissionData(Set<PermissionAttachmentInfo> permAttachments, String permBase) { // returns all parts of a permission eg {"rc", "home"}
		ArrayList<String> data = null;

		Iterator i = permAttachments.iterator();

		while(i.hasNext()) {
			PermissionAttachmentInfo permAttachment = (PermissionAttachmentInfo) i.next();

			if (permAttachment.getPermission().startsWith(permBase)) {
				data = new ArrayList<String>(Arrays.asList(permAttachment.getPermission().split(".")));
				return data;
			}
		}

		return data;
	}

	public static String getPermissionData(Set<PermissionAttachmentInfo> permAttachments, String permBase, int i) { // returns specified part of list
		ArrayList<String> data = getPermissionData(permAttachments, permBase);

		return data.get(i);
	}

	public static boolean inventoriesAreEqual(Inventory first, InventoryView view, Inventory backpack) {

		Player player = (Player) view.getPlayer();
		PlayerCache cache = RoyalCore.getCache(player);
		String backpackName = cache.getBackpackName();

		if(first == null && backpack == null)
			return true;

		if((first == null && backpack != null) || (first != null && backpack == null))
			return false;

		if(!view.getTitle().equals(backpackName))
			return false;

		//if(first.getType() != backpack.getType())
			//return false;

		ItemStack[] firstContents = first.getContents();
		ItemStack[] secondContents = backpack.getContents();

		if(firstContents.length != secondContents.length)
			return false;

		for(int i = 0; i < firstContents.length; i++) {
			if(firstContents[i] == null && secondContents[i] == null)
				continue;
			else if(firstContents[i] == null && secondContents[i] != null)
				return false;
			else if(secondContents[i] == null && firstContents[i] != null)
				return false;
			else if(!firstContents[i].equals(secondContents[i]))
				return false;
		}

		return true;
	}
}
