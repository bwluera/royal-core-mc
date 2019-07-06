package com.royalevolution.royalcommands;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.royalevolution.royalcommands.utils.Common;

public class ConfigManager {
	
	//registering the files
	//homes
	public FileConfiguration configfile;
	public File config;
	//endconfig
	//end registering files
	final Plugin plugin = RoyalCommands.getPlugin();
	
	
	public void filesSetup() {
		if (!plugin.getDataFolder().exists()) {
			//make data folder
			plugin.getDataFolder().mkdir();
		}
	//homes file creating stuff
	config = new File(plugin.getDataFolder(), "homes.yml");
	if (!config.exists()) {
		try {
			config.createNewFile();
			Common.log("Created homes.yml");
		} catch (IOException excep){
			Common.log("Can't create a homes.yml file");
		}
	}
	
	configfile = YamlConfiguration.loadConfiguration(config);
	
	}
	
	public void saveFiles() {
		try {
			configfile.save(config);
			Common.log("Saved homes.yml");
		} catch (IOException excep) {
			Common.log("Couldn't save the homes.yml");
		}
	}
	
	public void reloadFiles() {
		Common.log("Reloaded homes.yml");
		configfile = YamlConfiguration.loadConfiguration(config);
	}
	//TODO: add default values
}
