package com.nohaxjustprogram.genbuckets;

import com.sun.media.sound.InvalidFormatException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager
{
    private Main pl = Main.getInstance();
    private FileConfiguration shopGui;
    private FileConfiguration config;
    private YamlConfiguration shopYML = new YamlConfiguration();
    private File shopFile = null;

    public ConfigManager()
    {
        shopFile = new File(pl.getDataFolder(), "shop.yml");
        mkdir();
        loadYamls();
    }

    private void mkdir()
    {
        if (!shopFile.exists())
            pl.saveResource("shop.yml", false);
    }

    private void loadYamls()
    {
        try {
            shopYML.load(shopFile);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }

    }

    public YamlConfiguration getShopYML()
    {
        Bukkit.getServer().getLogger().log(Level.INFO, (shopYML.getList("gui.slots.0") == null) + " gui.slots.0 in yml is?");
        return shopYML;
    }

    public void reloadCfgs()
    {

    }
}
