package com.royalevolution.royalcommands.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class JoinLeaveListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
	event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] &6" + event.getPlayer().getName()));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
	event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c-&8] &6" + event.getPlayer().getName()));
	}
}
