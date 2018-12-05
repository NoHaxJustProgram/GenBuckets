package com.nohaxjustprogram.genbuckets.Integration;

import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;

public class Essentials
{
    private IEssentials essentials;

    public Essentials()
    {
        Setup();
    }

    private void Setup()
    {
        if (Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null)
        {
            essentials = (IEssentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        }
    }

    public IEssentials getEssentials()
    {
        return essentials;
    }
}
