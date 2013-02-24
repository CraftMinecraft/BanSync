package com.craftminecraft.plugins.bansync.config;

import org.bukkit.plugin.Plugin;

public class MainConfig extends Config{
	public MainConfig(Plugin plugin) {
		this.setFile(plugin);
	}
	
	public Boolean EnableLWC = true;
	public Boolean EnablePlotMe = true;
	public Boolean EnableVault = true;
	public Boolean EnableGriefPrevention = true;
}