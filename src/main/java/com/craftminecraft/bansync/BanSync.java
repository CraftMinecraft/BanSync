package com.craftminecraft.bansync;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;
//import com.worldcretornica.plotme.PlotMe;

public class BanSync extends JavaPlugin implements Listener {
	Logger Log = Logger.getLogger("Minecraft");
	private LWCPlugin pluginLWC;
	private Boolean hookedLWC = false;
	//private PlotMe pluginPlotMe;
	private Boolean hookedPlotMe = false;
	
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        // Hook into plugins
        hookLWC();
        //hookPlotMe();
        
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
    		// Player is banned, lets delete the locks
    		if (hookedLWC)
    		{
    			// Delete Player LWC Locks
    			deleteLWC(kickedplayer.getName());
    		}
    		
    		if (hookedPlotMe)
    		{
    			// Delete Player Plots
    			deletePlotMe();
    		}
    	}
    }
    
    private void hookLWC() {
    	Plugin p = this.getServer().getPluginManager().getPlugin("LWC");
    	if (p != null && p instanceof LWCPlugin) {
    		pluginLWC = (LWCPlugin) p;
    		Log.info("[BanSync] LWC was found, hooked into LWC");
    		hookedLWC = true;
    	} else {
    		Log.info("[BanSync] LWC was not found");
    	}
    }
    
    //private void hookPlotMe() {
    //	Plugin p = this.getServer().getPluginManager().getPlugin("PlotMe");
    //	if (p != null && p instanceof PlotMe) {
    //		pluginPlotMe = (PlotMe) p;
    //		Log.info("[BanSync] PlotMe was found, hooked into PlotMe");
    //		hookedPlotMe = true;
    //	} else {
    //		Log.info("[BanSync] PlotMe was not found");
    //	}
    //}
    
    private void deleteLWC(String toRemove) {
    	LWC lwc = pluginLWC.getLWC();
    	CommandSender sender = Bukkit.getConsoleSender();
    	lwc.fastRemoveProtectionsByPlayer(sender, toRemove, false);
    }
    
    private void deletePlotMe() {
    	
    }
}

