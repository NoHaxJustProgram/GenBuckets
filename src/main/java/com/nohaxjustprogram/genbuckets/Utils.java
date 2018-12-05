package com.nohaxjustprogram.genbuckets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String chat(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }


    public static ItemStack createItem(Inventory inv, Material material, int amount, int invSlot, String displayName, List<String> loreString)
    {
        ItemStack toReturn;
        toReturn = new ItemStack(material, amount);

        ItemMeta im = toReturn.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(loreString);

        toReturn.setItemMeta(im);
        inv.setItem(invSlot, toReturn);

        return toReturn;
    }

    public static ItemStack createItem(Inventory inv, int materialId, int byteId, int amount, int invSlot, String displayName, List<String> loreString)
    {
        ItemStack toReturn;

        toReturn = new ItemStack(Material.getMaterial(materialId), amount, (short) byteId);

        ItemMeta im = toReturn.getItemMeta();

        im.setDisplayName(displayName);

        im.setLore(loreString);
        toReturn.setItemMeta(im);
        inv.setItem(invSlot - 1, toReturn);

        return toReturn;
    }

}
