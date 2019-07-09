package com.royalevolution.royalcommands;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.royalevolution.royalcommands.commands.*;
import com.royalevolution.royalcommands.listeners.PlayerChatListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.royalevolution.royalcommands.listeners.JoinLeaveListener;
import com.royalevolution.royalcommands.utils.Common;

public class RoyalCommands extends JavaPlugin {

	private static final Cache<UUID, PlayerCache> playerCache = CacheBuilder.newBuilder()
			.maximumSize(1_000)
			.expireAfterWrite(240, TimeUnit.MINUTES)
			.build();

	private final static String chatPrefix = "&3&lR&b&loyal&3&lC&b&lommands &8> &r";

	private static RoyalCommands instance;

	@Override
	public void onEnable() { // all necessary plugin startup functions go here

		instance = this;

		Common.registerCommands(
				new CommandHat(),
				new CommandFly(),
				new CommandFeed(),
				new CommandHeal(),
				new CommandClearInv(),
				new CommandBroadcast(),
				new CommandHome(),
				new CommandSetHome(),
				new CommandRC()
				);
		Common.registerEvents(this, 
				new JoinLeaveListener(),
				new PlayerChatListener()
				);

		//adding custom recipes
		CustomRecipes.addCustomRecipes();
		
		loadConfigManager();

		Common.log("Initialized RoyalCommands. [Version " + this.getDescription().getVersion() + "]");
	}

	@Override
	public void onDisable() {
		instance = null;
	}
	
	private ConfigManager confm;
	
	public void loadConfigManager() {
		confm = new ConfigManager();
		confm.filesSetup();
		confm.saveFiles();
		confm.reloadFiles();
	}
	public void reload() {
		// TODO: make it so this function updates all mutable data and call it upon an 'rc reload' command

		PlayerCache.reloadFile(); // load /save playercache.dat

		// next, update all playercaches

		updatePlayerCaches();


	}

	public static void updatePlayerCaches() { // updates all players data read from playerCache.dat
		for (Player player : getOnlinePlayers()) {
			PlayerCache cache = getCache(player);

			cache.reloadCache();
		}
	}


	public static RoyalCommands getInstance() {
		return instance;
	}
	
	public static Plugin getPlugin() {
		return RoyalCommands.getPlugin(RoyalCommands.class);
	}

	public static PlayerCache getCache(UUID uuid) {
		PlayerCache cache = playerCache.getIfPresent(uuid);

		if (cache == null) {
			cache = new PlayerCache(uuid);

			playerCache.put(uuid, cache);
		}

		return cache;
	}

	public static PlayerCache getCache(Player player) {
		return getCache(player.getUniqueId());
	}

	public static String getChatPrefix() {
		return chatPrefix;
	}

	public static Collection<? extends Player> getOnlinePlayers() {
		return instance.getServer().getOnlinePlayers();
	}


}