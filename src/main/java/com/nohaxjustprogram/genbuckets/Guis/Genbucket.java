package com.nohaxjustprogram.genbuckets.Guis;

import com.nohaxjustprogram.genbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Genbucket {
    private static Main pl = Main.getInstance();
    public static Inventory inv;
    public static String inv_name;
    public static int rows;
    public static int columns;

    public static void initialize() {
        inv_name = pl.getConfig().getString("Gui.name");
        rows = pl.getConfig().getInt("Gui.rows");
        columns = pl.getConfig().getInt("Gui.columns");
        inv = Bukkit.createInventory(null, rows * columns);
    }

    public static Inventory GUI(Player p)
    {
        Inventory toReturn = Bukkit.createInventory(null, rows * columns, inv_name);
        toReturn.setContents(inv.getContents());
        String name = pl.getConfig().getString("genbucket.name");
        name = pl.color(name);
        List<String> lore = pl.colorList(pl.getConfig().getStringList("genbucket.lore"));
        ItemStack itemStack = pl.createItem(Material.LAVA_BUCKET, 1, (short) 0, name, lore);

        return toReturn;
    }
}
