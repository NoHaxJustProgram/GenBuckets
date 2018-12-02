package com.nohaxjustprogram.genbuckets;

import com.nohaxjustprogram.genbuckets.Commands.CmdGenBucket;
import com.nohaxjustprogram.genbuckets.Events.BucketEmptyEvent;
import com.nohaxjustprogram.genbuckets.Integration.Econ;
import com.nohaxjustprogram.genbuckets.Integration.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class Main extends JavaPlugin implements Listener {

    public Econ econ = new Econ();
    public Essentials ess = new Essentials();
    private static Main main;
    private ConfigManager configManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        main = this;
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new BucketEmptyEvent(), this);
        this.getCommand("genbucket").setExecutor(new CmdGenBucket());
        getConfig().options().copyDefaults(true);
        saveConfig();
        configManager = new ConfigManager();
    }

    public ConfigManager getConfigManager()
    {
        return configManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String color(String line) {
        line = ChatColor.translateAlternateColorCodes('&', line);
        return line;
    }

    //colors a string list
    public List<String> colorList(List<String> lore) {
        return lore.stream().map(this::color).collect(Collectors.toList());
    }

    public ItemStack createItem(Material material, int amount, short datavalue, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, datavalue);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color(name));
        meta.setLore(colorList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static Main getInstance()
    {
        return main;
    }

}
