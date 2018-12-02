package com.nohaxjustprogram.genbuckets.Guis;

import com.nohaxjustprogram.genbuckets.ConfigManager;
import com.nohaxjustprogram.genbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Genbucket {
    private static Main pl = Main.getInstance();
    public static Inventory inv;
    public static String inv_name;
    public static int rows;
    public static int columns;

    public static void initialize() {
        inv_name = pl.getConfigManager().getShopYML().getString("name");
        rows = pl.getConfigManager().getShopYML().getInt("rows");
        Bukkit.getServer().getLogger().log(Level.INFO, rows + " rows in genbucket class is?");
        inv = Bukkit.createInventory(null, rows * 9);
    }

    private static String name;
    private static List<ItemStack> itemStacks = new ArrayList<>();
    private static int i = 0;
    private static List<?> guiSlots;
    public static Inventory GUI(Player p)
    {
        initialize();
        p.sendMessage("ConfManager : inv_name " + (pl.getConfigManager().getShopYML() == null) + " : " + inv_name);
        Inventory toReturn = Bukkit.createInventory(null, rows * columns, inv_name);
        List<String> lore = pl.colorList(pl.getConfig().getStringList("genbucket.lore"));
        Bukkit.getServer().getLogger().log(Level.INFO, inv.getContents()[0] + " element 1 us?");
        toReturn.setContents(inv.getContents());
        name = pl.getConfig().getString("genbucket.name");
        guiSlots = pl.getConfigManager().getShopYML().getList("gui.slots." + i);
        guiSlots.iterator().forEachRemaining(o -> {
            name = pl.color(name.replace("%type%", guiSlots.toArray()[1].toString()));
            ItemStack itemStack = pl.createItem(Material.LAVA_BUCKET, 1, (short) 0, name, lore);
            itemStacks.add(itemStack);
            i++;
            guiSlots = pl.getConfigManager().getShopYML().getList("gui.slots." + i);
        });
        inv.setContents((ItemStack[]) itemStacks.toArray());
        return toReturn;
    }
}
