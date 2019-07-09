package com.royalevolution.royalcommands.listeners;

import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        if (!e.isAsynchronous()) {
            return;
        }

        Player player = e.getPlayer();
        String message = e.getMessage();

        if (player.hasPermission("rc.chatcolor")) {
            message = Common.colorize(message);
            e.setMessage(message);
        }

    }

}
