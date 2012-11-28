package com.craftminecraft.bansync;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.craftminecraft.bansync.log.Logger;
import com.craftminecraft.bansync.plugins.LWCPluginHook;

public class BanSync extends JavaPlugin implements Listener {
	public Logger logger = new Logger(this);
	private LWCPluginHook lwcplugin;
	
	
    public void onDisable() {
        // TODO: Place any custom disable code here.
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
    		
    	}
    }
    
    private void hookPlugins() {
    	// Hook LWC
    	lwcplugin = new LWCPluginHook(this);
    	lwcplugin.HookLWC();

    	// Hook PlotMe
    }
        
    //private void hookPlotMe() {
    //	Plugin p = this.getServer().getPluginManager().getPlugin("PlotMe");
    //	if (p != null && p instanceof PlotMe) {
    //		Log.info("[BanSync] PlotMe was found, hooked into PlotMe");
    //		hookedPlotMe = true;
    //	} else {
    //		Log.info("[BanSync] PlotMe was not found");
    //	}
    //}
    
    //private void deletePlotMe(String toRemove) {    	
    //	Log.info("[BanSync] Attempting to remove PlotMe Properties");
    	//for (World w : getServer().getWorlds())
    	//{
    //	World w = getServer().getWorld("world");
    //		HashMap<String, Plot> plots = PlotManager.getPlots(w);
    //		if (!plots.isEmpty())
    //		{
    //			for(String id : plots.keySet())
    //			{
    //				Plot plot = plots.get(id);

    //				Log.info("[BanSync - Debug] Found Plot " + plot.id + " Owner: " + plot.owner);
    				
    //				if (plot.owner == toRemove)
    //				{
    //					Log.info("[BanSync] Found a plot, removing it");
    //					String plotID = plot.id;
    				
    					//Location bottom = PlotManager.getPlotBottomLoc(w, plotID);
						//Location top = PlotManager.getPlotTopLoc(w, plotID);

						//PlotManager.clear(bottom, top);
    					
    //					PlotManager.removeOwnerSign(w, plotID);
    //					PlotManager.removeSellSign(w, plotID);
    				
    //					SqlManager.deletePlot(PlotManager.getIdX(plotID), PlotManager.getIdZ(plotID), w.getName().toLowerCase());
    //				}
    //			}
    //		}
    	//}
    //}
}

