package net.craftminecraft.bukkit.bansync.config;

import org.bukkit.plugin.Plugin;

public class MainConfig extends Config{
	public MainConfig(Plugin plugin) {
		this.setFile(plugin);
	}
	public Boolean AutoDeletePlayerOnBan = true;
	
	public Boolean EnableLWC = true;
	public Boolean EnablePlotMe = true;
	public Boolean EnableVault = true;
	public Boolean EnableGriefPrevention = true;
	public Boolean EnableWorldGuard = true;
	public Boolean EnableEssentials = true;
}