package com.nohaxjustprogram.genbuckets.Listeners;

import com.nohaxjustprogram.genbuckets.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GenBucketGUIClickListener implements Listener
{
    private Main pl = Main.getInstance();
    private double cost;
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if (e.getCurrentItem() == null)
            return;

        if (!e.getInventory().getTitle().equals(pl.color(pl.getConfigManager().getShopYML().get("name").toString())))
            return;

        if (!pl.getEcon().getEcon().has((OfflinePlayer) e.getWhoClicked(), cost))
            return;
    }
}
