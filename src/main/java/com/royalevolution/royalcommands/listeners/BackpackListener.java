package com.royalevolution.royalcommands.listeners;

import com.royalevolution.royalcommands.PlayerCache;
import com.royalevolution.royalcommands.RoyalCore;
import com.royalevolution.royalcommands.utils.Common;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class BackpackListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        if (e.getView().getTopInventory().getType() == InventoryType.CRAFTING
                || e.getView().getBottomInventory().getType() == InventoryType.CRAFTING) {

            return;
        }

        Player player = (Player) e.getPlayer();

        PlayerCache cache = RoyalCore.getCache(player);

        Inventory inv = e.getView().getTopInventory(); // inventory that could be the backpack
        // We need to make sure the inventory being opened is the exact same as the backpack
        boolean isSameInv = Common.inventoriesAreEqual(inv, e.getView(), cache.getBackpack());

        cache.setViewingBackpack(isSameInv); // we'll use this variable to save it upon closing
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        if (e.getView().getTopInventory().getType() == InventoryType.CRAFTING
                || e.getView().getBottomInventory().getType() == InventoryType.CRAFTING) {

            return;
        }

        Player player = (Player) e.getPlayer();

        PlayerCache cache = RoyalCore.getCache(player);

        Inventory inv = e.getView().getTopInventory();

        if (cache.isViewingBackpack()) {
            cache.setBackpackContents(inv.getContents());
        }
    }

}
