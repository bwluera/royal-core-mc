package com.royalevolution.royalcommands;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.royalevolution.royalcommands.utils.Common;

public class ConfigManager {
	
	//registering the files
	//config
	public FileConfiguration configfile;
	public File config;
	//endconfig
	//end registering files
	final Plugin plugin = RoyalCore.getPlugin();
	
	
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
			Common.log("Created config.yml");
		} catch (IOException excep){
			Common.log("Can't create a config.yml file");
		}
	}
	
	configfile = YamlConfiguration.loadConfiguration(config);
	
	}
	
	public void saveFiles() {
		try {
			configfile.save(config);
			Common.log("Saved config.yml");
		} catch (IOException excep) {
			Common.log("Couldn't save the config.yml");
		}
	}
	
	public void reloadFiles() {
		Common.log("Reloaded config.yml");
		configfile = YamlConfiguration.loadConfiguration(config);
	}
	//TODO: add default values
}
