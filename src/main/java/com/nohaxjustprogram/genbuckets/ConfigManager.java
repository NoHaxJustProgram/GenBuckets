package com.nohaxjustprogram.genbuckets;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager
{
    private Main pl = Main.getInstance();
    private FileConfiguration shopGui;
    private FileConfiguration config;
    private JSONObject shopJSON;
    private JSONParser shopParser;
    private File shopFile = null;

    public ConfigManager()
    {
        shopFile = new File(pl.getDataFolder(), "shop.json");
        shopParser = new JSONParser();
        mkdir();
        loadJsons();
    }

    private void mkdir()
    {
        if (!shopFile.exists())
            pl.saveResource("shop.json", false);
    }

    private void loadJsons()
    {
        try {
            shopJSON = (JSONObject) shopParser.parse(new FileReader(shopFile));
        }
        catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

    }

    public JSONObject getShopYML()
    {
        //Bukkit.getServer().getLogger().log(Level.INFO, (shopJSON.get("gui.slots.0") == null) + " gui.slots.0 in yml is?");
        return shopJSON;
    }

    public JSONParser getShopParser() {
        return shopParser;
    }

    public File getShopFile() {
        return shopFile;
    }

    public void reloadCfgs()
    {

    }
}
