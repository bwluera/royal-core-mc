package com.royalevolution.royalcommands;

import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerCache {

    private static FileConfiguration cache = null;

    final static File file = new File("plugins/RoyalCommands/playerCache.dat");

    static {
        if (file.exists()) {
            cache = YamlConfiguration.loadConfiguration(file);

            try {
                cache.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                Common.log(RoyalCommands.getChatPrefix() + "FATAL: Failed to load 'playerCache.yml'!");
                e.printStackTrace();
            }
        } else {
            RoyalCommands.getInstance().saveResource("playerCache.dat", false);
            cache = YamlConfiguration.loadConfiguration(file);

            Common.log(RoyalCommands.getChatPrefix() + "File playerCache.dat successfully created.");
        }
    }

    private final UUID uuid;

    private HashMap<String, Location> homes = new HashMap();

    public PlayerCache(UUID uuid) {
        this.uuid = uuid;

        onLoad();
    }

    private void onLoad() { // main method to load all data from player cache

    }

    public void save() { // main method to save all data to the player cache file
                        // WARNING: always use the save method before any load methods
        saveHomes();

        try {
            cache.save(file);
            cache.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void reloadFile() { // contains all methods to load player data
        try {
            cache.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Common.log(RoyalCommands.getChatPrefix() + "Exception occurred while reloading 'playerCache.dat'!");
            e.printStackTrace();
        }
    }

    public void reloadCache() { // use this when only updating one players data. refer to RoyalCommands.updatePlayerCaches() to update all players data
        loadHomes();
    }

    // all sub-methods for saving / loading player data go in this section

    public void saveHomes() {
        if (!homes.isEmpty()) {
            Set<String> homeNames = homes.keySet();
            Iterator i = homeNames.iterator();



            while (i.hasNext()) {
                String homeName = String.valueOf(i.next());

                Location l = homes.get(homeName);

                String homeKey = l.getWorld().getName() + "|" + l.getX() + "|" + l.getY() + "|" + l.getZ() + "|" + l.getYaw() + "|" + l.getPitch();

                cache.set(uuid + ".homes." + homeName, homeKey);
            }

        }
    }

    public void loadHomes() {
        homes.clear(); // we need to clear the homes before adding all the ones from the config (would add duplicates)
                    // therefore, we should always use the save() method before using this
        for (String homeName : cache.getConfigurationSection(uuid + ".homes").getKeys(false)) {
            Location location = getHomeLocation(homeName);
            homes.put(homeName, location);
        }

    }

    // All player data related methods go below here

    public void delHome(String homeName) {
        if (homeExists(homeName)) {
            cache.set(uuid + ".homes." + homeName, null);
            save();
        }
    }

    public void addHome(String homeName, Location location) {
        homes.put(homeName, location);
        save();
    }

    public HashMap getHomes() {
        return homes;
    }

    public boolean homeExists(String homeName) {
        return cache.contains(uuid + ".homes." + homeName);
    }

    public Location getHomeLocation(String homeName) {
        if (!homeExists(homeName)) {
            return null;
        }
        else {
            String[] data = cache.getString(uuid + ".homes." + homeName).split("\\|"); // TODO: split could cause null

            double[] pos = new double[5];

            for (int i = 0; i <= 4; i++)
                pos[i] = Double.parseDouble(data[i+1]);

            Location homeLocation = new Location(Bukkit.getWorld(data[0]), pos[0], pos[1], pos[2], (float) pos[3], (float) pos[4]);

            return homeLocation;
        }

    }

}
