package com.royalevolution.royalcommands;

import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerCache {

    private final FileConfiguration cache;
    final File file = new File("plugins/RoyalCommands/playerCache.dat");

    private final UUID uuid;

    private HashMap<String, Location> homes = new HashMap();

    public PlayerCache(UUID uuid) {
        if (file.exists()) {
            cache = YamlConfiguration.loadConfiguration(file);

            try {
                cache.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                Common.log(RoyalCommands.getPrefix() + "FATAL: Failed to load 'playerCache.yml'!");
                e.printStackTrace();
            }
        } else {
            RoyalCommands.getInstance().saveResource("playerCache.dat", false);
            cache = YamlConfiguration.loadConfiguration(file);

            Common.log(RoyalCommands.getPrefix() + "File playerCache.dat successfully created.");
        }

        this.uuid = uuid;

        onLoad();
    }

    private void onLoad() {
    }

    public void save() {
        saveHomes();

        try {
            cache.save(file);
            cache.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveHomes() {
        if (!homes.isEmpty()) {
            Set<String> homeNames = homes.keySet();
            Iterator i = homeNames.iterator();



            while (i.hasNext()) {
                String homeName = String.valueOf(i.next());
                Common.log(homeName);
                Location l = homes.get(homeName);

                String homeKey = l.getWorld().getName() + "|" + l.getX() + "|" + l.getY() + "|" + l.getZ() + "|" + l.getYaw() + "|" + l.getPitch();

                Common.log(homeKey);

                cache.set(uuid + ".homes." + homeName, homeKey);
            }

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
