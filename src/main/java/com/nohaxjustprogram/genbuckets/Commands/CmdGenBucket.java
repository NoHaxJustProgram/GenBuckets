package com.nohaxjustprogram.genbuckets.Commands;

import com.nohaxjustprogram.genbuckets.Guis.Genbucket;
import com.nohaxjustprogram.genbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CmdGenBucket implements CommandExecutor
{
    private Main plugin = Main.getInstance();
    public CmdGenBucket()
    {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission(plugin.getConfig().getString("genbucket.give-perms"))) {
                    sender.sendMessage(plugin.color(plugin.getConfig().getString("messages.nopermissions")));
                    return true;
                }
                if (Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isOnline()) {
                    sender.sendMessage(plugin.color(plugin.getConfig().getString("messages.founderrors.player")).replace("%player%", args[1]));
                    return true;
                }
                if (Bukkit.getServer().getPlayer(args[1]).isOnline()) {
                    ItemStack genBucket = plugin.createItem(Material.WATER_BUCKET,Integer.parseInt(args[3]),(short)0,plugin.color(plugin.getConfig().getString("genbucket.name")).replace("%type%", args[2]),plugin.colorList(plugin.getConfig().getStringList("genbucket.lore")));
                    Bukkit.getServer().getPlayer(args[1]).getInventory().addItem(genBucket);
                    return true;
                }
            }
        }
        if ((args.length == 1 || args.length == 0) && sender instanceof Player)
        {
            Genbucket.GUI((Player) sender);
        }

        return true;
    }

}
