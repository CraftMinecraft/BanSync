package net.craftminecraft.bukkit.bansync.config;

import java.io.File;

import org.bukkit.plugin.Plugin;

public class MainConfig extends Config{
	public MainConfig(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        CONFIG_HEADER = "BanSync Configuration File";
	}
	
	// General Settings
	public Boolean General_AutoDeleteOnBan = true;
	public Boolean General_CheckForUpdates = true;
	
	// Enabled Plugin Settings
	public Boolean Plugin_LWC_Enabled = true;
	
	public Boolean Plugin_PlotMe_Enabled = true;
	
	public Boolean Plugin_Vault_Enabled = true;
	
	public Boolean Plugin_GriefPrevention_Enabled = true;
	
	public Boolean Plugin_WorldGuard_Enabled = true;
	public Boolean Plugin_WorldGuard_DeletePlotIfNoOwnersLeft = true;
	
	public Boolean Plugin_Essentials_Enabled = true;
}