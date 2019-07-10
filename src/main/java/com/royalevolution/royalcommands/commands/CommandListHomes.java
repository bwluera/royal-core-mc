package com.royalevolution.royalcommands.commands;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class CommandListHomes extends PlayerCommand {

    public CommandListHomes() {
        super("homes");

        setAliases(Arrays.asList("listhomes"));
        setDescription("Displays the names of your current set homes.");
        setUsage("/homes [page]");
    }

    @Override
    protected void run(Player sender, String[] args) {
        PlayerCache cache = RoyalCore.getCache(sender);

        if (args.length == 0) {
            if (cache.getHomes().isEmpty()) {
                Common.tell(sender, RoyalCore.getChatPrefix() + "You don't have any homes set. Use /sethome!");
                return;
            }

            Set<String> homes = cache.getHomes().keySet();
            Iterator i = homes.iterator();

            String page = RoyalCore.getChatPrefix();

            while (i.hasNext()) {

            }


        }
    }
}
