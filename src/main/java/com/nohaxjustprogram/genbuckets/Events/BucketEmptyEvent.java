package com.nohaxjustprogram.genbuckets.Events;

import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.nohaxjustprogram.genbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BucketEmptyEvent implements Listener
{

    private Main plugin = Main.getInstance();
    private BlockFace blockFace;
    private FLocation flocation;
    private boolean cancel;
    private Block block;
    private int id;
    @EventHandler
    public void bucketEmptyEvent(PlayerBucketEmptyEvent event)
    {
        Entity entity = event.getPlayer();
        block = event.getBlockClicked();
        blockFace = event.getBlockFace();
        Block blockNext = block.getRelative(blockFace);

        Player player = event.getPlayer();
        FPlayers fplayers = FPlayers.getInstance();
        FPlayer fplayer = fplayers.getByPlayer(player);
        Faction faction = null;

        player.sendMessage(blockFace.toString() + " bf");

        ItemStack itemInHand = player.getItemInHand();
        ItemMeta im = itemInHand.getItemMeta();
        if (im.hasDisplayName() && im.hasLore())
        {
            if (im.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("genbucket.name").replace("%type%", im.getDisplayName().split(" ")[0])))
                    && im.getLore().toString().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("genbucket.lore").toString())))
            {
                if (fplayer.getFaction().isWilderness() || fplayer.hasFaction())
                {
                    faction = fplayer.getFaction();
                    flocation = new FLocation(block);
                    Set<FLocation> factionClaims = faction.getAllClaims();

                    if (factionClaims.contains(flocation))
                    {
                        if (!(blockNext.getType().equals(Material.AIR)))
                        {
                            blockFace = BlockFace.DOWN;
                            if (!(block.getRelative(blockFace).getType().equals(Material.AIR)))
                                blockFace = BlockFace.UP;
                        }

                        String genBucketType = im.getDisplayName().split(" ")[0];
                        event.setCancelled(true);
                        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage("");
                                Block genBlock = block.getRelative(blockFace);
                                block = genBlock;
                                flocation = new FLocation(genBlock.getLocation());
                                if (!(!factionClaims.contains(flocation) ^ ((genBlock.getType().equals(Material.AIR)) || (genBlock.getType().equals(Material.valueOf(genBucketType))) || (genBlock.getType().equals(Material.BEDROCK))) ^ (flocation.getWorld().getMaxHeight() == genBlock.getLocation().getBlockY() || genBlock.getLocation().getBlockY() == 0)))
                                {
                                    cancel = true;
                                    cancelTask(id);
                                    return;
                                }
                                genBlock.setType(Material.valueOf(genBucketType.toUpperCase()));
                                genBlock.getState().update();
                            }
                        }, 0L, plugin.getConfig().getInt("genbucket.delay"));



                    }
                    else
                        player.sendMessage(plugin.color(plugin.getConfig().getString("messages.cannotaccessblock")));
                }
                else
                    player.sendMessage(plugin.color(plugin.getConfig().getString("messages.notinfaction")));
            }
        }
    }

    public void cancelTask(int id)
    {
        Bukkit.getScheduler().cancelTask(id);
    }
}
