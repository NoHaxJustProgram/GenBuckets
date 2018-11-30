package com.nohaxjustprogram.genbuckets.Integration;

import com.earth2me.essentials.Worth;
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

    public Worth getWorth()
    {
        return essentials.getWorth();
    }
}
