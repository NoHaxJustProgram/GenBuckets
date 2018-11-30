package com.nohaxjustprogram.genbuckets.Integration;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;

public class Econ
{
    private static Economy econ = null;
    private boolean econEnabled = false;
    private static Permission perms = null;
    private static Chat chat = null;

    public Econ()
    {
        setup();
    }

    private void setup()
    {
        if (econ == null)
        {
            String integrationFail = "Economy integration is " + (econEnabled ? "enabled, but" : "disabled, and") + " the plugin \"Vault\"";
            if (Bukkit.getPluginManager().getPlugin("Vault") == null)
            {
                Bukkit.getLogger().log(Level.SEVERE, integrationFail);
            }
            else
            {
                RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
                if (rsp == null)
                {
                    Bukkit.getLogger().log(Level.SEVERE, integrationFail + "is not hooked into an economy plugin.");
                }
                else
                {
                    econ = rsp.getProvider();
                    Bukkit.getLogger().log(Level.INFO, "Economy integration through Vault plugin was successful");
                }
            }
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEcon()
    {
        return econ;
    }

    public static Permission getPerms()
    {
        return perms;
    }

    public static Chat getChat()
    {
        return chat;
    }
}
