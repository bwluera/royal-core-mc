package com.royalevolution.royalcommands.commands;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCore;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandBackpack extends PlayerCommand {
    public CommandBackpack() {
        super("backpack");

        setAliases(Arrays.asList("rcbp"));
        setDescription("Opens up your backpack.");
        setUsage("/backpack");
    }

    @Override
    protected void run(Player sender, String[] args) {
        // open backpack here
        PlayerCache cache = RoyalCore.getCache(sender);

        sender.openInventory(cache.getBackpack());
    }
}
