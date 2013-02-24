package com.craftminecraft.plugins.bansync.plugins;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.craftminecraft.plugins.bansync.BanSync;
import com.craftminecraft.plugins.bansync.log.LogLevels;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;

public class LWCPluginHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	private LWCPlugin lwcplugininterface;
	
	public LWCPluginHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	public Boolean HookLWC()
	{
		// Attempt to hook into LWC
    	Plugin p = bansyncinterface.getServer().getPluginManager().getPlugin("LWC");
    	if (p != null && p instanceof LWCPlugin) {
    		pluginHooked = true;
    		lwcplugininterface = (LWCPlugin) p;
    		bansyncinterface.logger.log(LogLevels.INFO, "LWC Found, hooking into LWC.");
    		return true;
    	} else {
    		pluginHooked = false;
    		bansyncinterface.logger.log(LogLevels.INFO, "LWC not Found");
    		return false;
    	}
	}
	
	public void ClearLWCLocks(String playerName)
	{
		bansyncinterface.logger.log(LogLevels.INFO, "Removing LWC Locks");
		LWC lwc = lwcplugininterface.getLWC();
    	CommandSender sender = Bukkit.getConsoleSender();
    	lwc.fastRemoveProtectionsByPlayer(sender, playerName, false);
	}
}
