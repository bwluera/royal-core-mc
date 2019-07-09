package com.royalevolution.royalcommands.commands;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCommands;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Arrays;
import java.util.Set;

public class CommandSetHome extends PlayerCommand {

    public CommandSetHome() {
        super("sethome");

        setAliases(Arrays.asList("homeset", "seth"));
        setDescription("Sets a home location wherever you're standing.");
        setUsage("/sethome [homeName]");
    }

    @Override
    protected void run(Player sender, String[] args) {
        if (!sender.hasPermission("rc.sethome")) { // no perms to set home
            Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou don't have permission to set a home!");
            return;
        }

        PlayerCache cache = RoyalCommands.getCache(sender.getUniqueId());

        int allowedHomes = allowedHomes(sender);

        if (cache.getHomes().size() == allowedHomes) { // max homes reached
            Common.tell(sender, RoyalCommands.getChatPrefix() + "&cFailed to set home: You've reached your max number of homes!");
            return;
        }

        if (args.length == 0) {
            int homeNum = cache.getHomes().size() + 1;
            String homeName = "home" + (homeNum == 1 ? "" : homeNum);
            cache.addHome(homeName, sender.getLocation());

            Common.tell(sender, RoyalCommands.getChatPrefix() + "Successfully set a home named '" + homeName + "'.");
        }
        else if (args.length == 1) {
            cache.addHome(args[0], sender.getLocation());

            Common.tell(sender, RoyalCommands.getChatPrefix() + "Successfully set a home named '" + args[0] + "'.");
        }
        else {
            Common.tell(sender, RoyalCommands.getChatPrefix() + "&cYou've entered too many arguments! &rUsage: /sethome [homeName]");
        }
    }

    public int allowedHomes(Player player) {
        Set<PermissionAttachmentInfo> permissionAttachments = player.getEffectivePermissions();

        int allowedHomes = Integer.parseInt(Common.getPermissionData(permissionAttachments, "rc.homemultiple") == null ? "1" : Common.getPermissionData(permissionAttachments, "rc.homemultiple", 2));

        if (player.hasPermission("rc.homeunlimited"))
            allowedHomes = -1;

        return allowedHomes;
    }

}
