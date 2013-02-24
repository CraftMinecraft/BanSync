package com.craftminecraft.plugins.bansync.plugins;

import org.bukkit.plugin.Plugin;

import com.craftminecraft.plugins.bansync.BanSync;
import com.craftminecraft.plugins.bansync.log.LogLevels;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class GriefPreventionHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	
	public GriefPreventionHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	public Boolean HookGriefPrevention()
	{
		// Attempt to hook into LWC
    	Plugin p = bansyncinterface.getServer().getPluginManager().getPlugin("GriefPrevention");
    	if (p != null && p instanceof GriefPrevention) {
    		pluginHooked = true;
    		bansyncinterface.logger.log(LogLevels.INFO, "Grief Prevention Found, hooking into Grief Prevention.");
    		return true;
    	} else {
    		pluginHooked = false;
    		bansyncinterface.logger.log(LogLevels.INFO, "Grief Prevention not Found");
    		return false;
    	}
	}
	
	public void ClearGriefPreventionLocks(String playerName)
	{
		bansyncinterface.logger.log(LogLevels.INFO, "Removing Claims from Grief Prevention");
    	
    	GriefPrevention.instance.dataStore.deleteClaimsForPlayer(playerName, true);
	}
}
