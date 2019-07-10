package com.royalevolution.royalcommands.commands;

import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CommandRC extends Command {

    String chatPrefix = RoyalCore.getChatPrefix();
    String prefix = chatPrefix.substring(0, chatPrefix.length()-7) + "&r";

    public CommandRC() {
        super("rc");

        setAliases(Arrays.asList("royalcommands", "royalcommand"));
        setDescription("The RoyalCommands' administrator command.");

    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        // sender type shouldn't matter as this command won't modify player data / players

        if (args.length == 0) { // print available commands
            sendCommands(sender);
        }
        else if (args.length == 1) {
            if (args[0].toLowerCase().equals("reload")) {
                RoyalCore.getInstance().reload();
                Common.tell(sender, chatPrefix + "Successfully reloaded the plugin.");
            }
        }


        return true;
    }

    public void sendCommands(CommandSender sender) { // TODO: should only list commands they have perms for
        Common.tell(sender, "&b===== &r" + prefix + "&r &3&lL&rist" + "&b=====", // TODO: make this look better
                                     "/rc reload");
    }

}
