package com.royalevolution.royalcommands;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.royalevolution.royalcommands.utils.Common;

public class ConfigManager {
	private RoyalCommands plugin = RoyalCommands.getPlugin(RoyalCommands.class);
	
	//registering the files
	//config
	public FileConfiguration configfile;
	public File config;
	//endconfig
	//end registering files
	
	
	
	public void filesSetup() {
		if (!plugin.getDataFolder().exists()) {
			//make data folder
			plugin.getDataFolder().mkdir();
		}
	//config file creating stuff
	config = new File(plugin.getDataFolder(), "config.yml");
	if (!config.exists()) {
		try {
			config.createNewFile();
		} catch (IOException excep){
			Common.log("Can't create a config.yml file");
		}
	}
	
	configfile = YamlConfiguration.loadConfiguration(config);
	
	}
}
