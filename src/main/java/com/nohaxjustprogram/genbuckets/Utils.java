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


    public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, String... loreString)
    {
        ItemStack toReturn;

        List<String> lore = new ArrayList<>();

        toReturn = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta im = toReturn.getItemMeta();

        im.setDisplayName(Utils.chat(displayName));
        for (String s : loreString)
        {
            lore.add(Utils.chat(s));
        }

        im.setLore(lore);
        toReturn.setItemMeta(im);
        inv.setItem(invSlot - 1, toReturn);

        return toReturn;
    }

    public static ItemStack createItem(Inventory inv, int materialId, int byteId, int amount, int invSlot, String displayName, String... loreString)
    {
        ItemStack toReturn;

        List<String> lore = new ArrayList<>();

        toReturn = new ItemStack(Material.getMaterial(materialId), amount, (short) byteId);

        ItemMeta im = toReturn.getItemMeta();

        im.setDisplayName(Utils.chat(displayName));
        for (String s : loreString)
        {
            lore.add(Utils.chat(s));
        }

        im.setLore(lore);
        toReturn.setItemMeta(im);
        inv.setItem(invSlot - 1, toReturn);

        return toReturn;
    }

}
