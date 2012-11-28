package com.craftminecraft.bansync;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.craftminecraft.bansync.log.Logger;
import com.craftminecraft.bansync.plugins.LWCPluginHook;
import com.craftminecraft.bansync.plugins.PlotMePluginHook;
import com.craftminecraft.bansync.plugins.VaultPluginHook;

public class BanSync extends JavaPlugin implements Listener {
	public Logger logger = new Logger(this);
	private LWCPluginHook lwcplugin;
	private PlotMePluginHook plotmeplugin;
	private VaultPluginHook vaultplugin;
	
    public void onDisable() {
        lwcplugin = null;
        plotmeplugin = null;
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        // Hook into plugins
        hookPlugins();
        
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome, " + event.getPlayer().getDisplayName() + "!");
    }
    
    @EventHandler
    public void onPlayerKicked(PlayerKickEvent event) {
    	Player kickedplayer;
    	kickedplayer = event.getPlayer();
    	
    	Boolean PlayerBanned = false;
    	
    	// Check to see if user is banned - Vanilla
    	if (kickedplayer.isBanned())
    		PlayerBanned = true;
    	
    	// Check to see if user is banned with Essentials
    	
    	
    	if (PlayerBanned)
    	{
    		// Clear LWC Locks
    		if (lwcplugin.isHooked())
    			lwcplugin.ClearLWCLocks(kickedplayer.getName());
    		
    		// Delete PlotMe Plots
    		if (plotmeplugin.isHooked())
    			plotmeplugin.ClearPlotMePlots(kickedplayer.getName());
    		
    		// Delete Players Economy
    		if (vaultplugin.isHooked())
    			vaultplugin.ClearEconomy(kickedplayer.getName());
    	}
    }
    
    private void hookPlugins() {
    	// Hook LWC
    	lwcplugin = new LWCPluginHook(this);
    	lwcplugin.HookLWC();

    	// Hook PlotMe
    	plotmeplugin = new PlotMePluginHook(this);
    	plotmeplugin.HookPlotMe();
    	
    	// Hook Econony
    	vaultplugin = new VaultPluginHook(this);
    	vaultplugin.HookVault();
    }
}

