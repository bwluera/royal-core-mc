package com.royalevolution.royalcommands.listeners;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class BackpackListener implements Listener {

    @EventHandler // we need to make sure that the player is in their backpack and not a renamed one or something
    public void onInventoryOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();

        PlayerCache cache = RoyalCore.getCache(player);

        Inventory inv = e.getView().getTopInventory();

        boolean isSameInv = Common.inventoriesAreEqual(inv, e.getView(), cache.getBackpack());

        cache.setViewingBackpack(isSameInv);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        PlayerCache cache = RoyalCore.getCache(player);

        Inventory inv = e.getView().getTopInventory();

        if (inv != null && cache.isViewingBackpack()) {
            cache.setBackpackContents(inv.getContents());
        }
    }

}
