package com.royalevolution.royalcommands.commands;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandHome extends PlayerCommand {

    public CommandHome() {
        super("home");

        setAliases(Arrays.asList("home", "rchome"));
        setDescription("Teleports you to an existing home.");
        setUsage("/home [home]");
    }

    @Override
    protected void run(Player sender, String[] args) {
        PlayerCache cache = RoyalCommands.getCache(sender.getUniqueId());

        if (args.length == 0) { // player wants to go to default home
            int homes = cache.getHomes().size();

            if (homes == 1) {
                String homeName = String.valueOf(cache.getHomes().keySet().iterator().next());
                Location home = cache.getHomeLocation(homeName);
                sender.teleport(home);
            }
            else { // TODO: list homes with /homes | /listhomes
                Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou " + (homes > 1 ? "have more than one home! Please specify the home name." : "haven't set a home! Please set a home with /sethome."));
            }
        }
        else if (args.length == 1) {
            if (!cache.homeExists(args[0])) {
                Common.tell(sender, RoyalCommands.getChatPrefix() + "&cError: Home does not exist.");
                return;
            }
            Location home = cache.getHomeLocation(args[0]);
            sender.teleport(home);
        }
        else {
            Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou've entered too many arguments! &rUsage: /sethome [homeName]");
        }
    }
}
