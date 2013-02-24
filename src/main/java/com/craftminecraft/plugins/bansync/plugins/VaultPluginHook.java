package com.craftminecraft.plugins.bansync.plugins;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

import com.craftminecraft.plugins.bansync.BanSync;
import com.craftminecraft.plugins.bansync.log.LogLevels;

public class VaultPluginHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	public static Economy econ = null;
	
	public VaultPluginHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	public Boolean HookVault()
	{
    	Plugin p = bansyncinterface.getServer().getPluginManager().getPlugin("Vault");
    	if (p != null && p instanceof Vault) {
    		pluginHooked = true;
    		bansyncinterface.logger.log(LogLevels.INFO, "Vault Found, hooking into Vault.");
    		RegisteredServiceProvider<Economy> rsp = bansyncinterface.getServer().getServicesManager().getRegistration(Economy.class);
    		econ = rsp.getProvider();
    		return true;
    	} else {
    		pluginHooked = false;
    		bansyncinterface.logger.log(LogLevels.INFO, "Vault not Found");
    		return false;
    	}
	}
	
	public void ClearEconomy(String playerName)
	{
		bansyncinterface.logger.log(LogLevels.INFO, "Removing Players Economy");
		
		double balance = econ.getBalance(playerName);
		
		bansyncinterface.logger.log("[Debug] Player had a balance of: " + balance);
		
		econ.withdrawPlayer(playerName, balance);
		econ.deleteBank(playerName);
	}
}
