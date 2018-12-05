package com.nohaxjustprogram.genbuckets.Guis;

import com.nohaxjustprogram.genbuckets.ConfigManager;
import com.nohaxjustprogram.genbuckets.Main;
import com.nohaxjustprogram.genbuckets.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Genbucket {
    private static Main pl = Main.getInstance();
    public static Inventory inv;
    public static String inv_name = "";
    public static int size = 0;

    public static void initialize() {
        inv_name = (String) pl.getConfigManager().getShopYML().get("name");
        size = Integer.parseInt(Long.toString((long) pl.getConfigManager().getShopYML().get("rows")));
        inv = Bukkit.createInventory(null, size * 9);
        guiSlots = new ArrayList<>();
        name = pl.getConfig().getString("genbucket.name");
    }

    private static String name = "";
    private static Object guiSlots = null;

    public static Inventory GUI(Player p) {
        initialize();
        Inventory toReturn = Bukkit.createInventory(null, size * 9, inv_name);
        List<String> lore = pl.colorList(pl.getConfig().getStringList("genbucket.lore"));
        toReturn.setContents(inv.getContents());

        JSONParser jsonParser = pl.getConfigManager().getShopParser();
        try
        {
            Object obj = jsonParser.parse(new FileReader(pl.getConfigManager().getShopFile()));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray jarr;
            JSONObject jobj;
            JSONObject slots = (JSONObject) jsonObj.get("slots");
            Bukkit.getLogger().log(Level.INFO, ((JSONObject) ((JSONArray) slots.get(String.valueOf(3))).get(0)).get("name").toString());
            for (int i = 0; i < slots.size(); i++)
            {
                jarr = (JSONArray) slots.get(String.valueOf(i));
                jobj = (JSONObject) jarr.get(0);
                name = pl.color(name.replace("%type%", jobj.get("name").toString()));
                Utils.createItem(inv, Material.valueOf(ChatColor.stripColor(name).split(" ")[0].toUpperCase()), 1, i, name, lore);
                name = pl.getConfig().getString("genbucket.name");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        toReturn.setContents(inv.getContents());
        return toReturn;
    }
}