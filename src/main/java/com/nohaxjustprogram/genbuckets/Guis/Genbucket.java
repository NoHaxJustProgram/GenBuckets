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
    public static int size;
    public static int columns;

    public static void initialize() {
        inv_name = pl.getConfigManager().getShopYML().getString("name");
        size = pl.getConfigManager().getShopYML().getInt("rows");
        inv = Bukkit.createInventory(null, size * 9);
    }

    private static String name;
    private static List<ItemStack> itemStacks = new ArrayList<>();
    private static int i = 0;
    private static List<?> guiSlots = null;
    public static Inventory GUI(Player p)
    {
        initialize();
        Inventory toReturn = Bukkit.createInventory(null, size * 9, inv_name);
        List<String> lore = pl.colorList(pl.getConfig().getStringList("genbucket.lore"));
        toReturn.setContents(inv.getContents());
        name = pl.getConfig().getString("genbucket.name");
        guiSlots = pl.getConfigManager().getShopYML().getList("gui.slots." + i);
        Bukkit.getServer().getLogger().log(Level.INFO, (guiSlots == null) + " guislots = null?");

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
// does nothing