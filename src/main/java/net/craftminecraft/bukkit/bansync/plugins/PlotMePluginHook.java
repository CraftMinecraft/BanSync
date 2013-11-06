package net.craftminecraft.bukkit.bansync.plugins;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import net.craftminecraft.bukkit.bansync.BanSync;
import net.craftminecraft.bukkit.bansync.log.LogLevels;
import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;
import com.worldcretornica.plotme.SqlManager;


public class PlotMePluginHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	
	public PlotMePluginHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	public Boolean HookPlotMe()
	{
		Plugin p = bansyncinterface.getServer().getPluginManager().getPlugin("PlotMe");
	    if (p != null && p instanceof PlotMe) {
	    	bansyncinterface.logger.log(LogLevels.INFO, "PlotMe was found, hooked into PlotMe");
	    	pluginHooked = true;
	    	return true;
	    } else {
	    	pluginHooked = false;
	    	bansyncinterface.logger.log(LogLevels.INFO, "PlotMe not Found");
	    	return false;
	    }	
	}
	
    public void ClearPlotMePlots(String playerName) {
        bansyncinterface.logger.log(LogLevels.INFO, "Removing PlotMe Plots");

        List<World> worlds = bansyncinterface.getServer().getWorlds();
        for (World w : worlds) {
            if (PlotManager.isPlotWorld(w)) {
                HashMap<String, Plot> plots;
                plots = PlotManager.getPlots(w);
                if (plots.isEmpty()) {
                    continue;
                }
                    
                for (String id : plots.keySet()) {
                    Plot plot = plots.get(id);
                    
                    if (plot.owner.equalsIgnoreCase(playerName)) {
                        bansyncinterface.logger.log(LogLevels.INFO, "Found plot " + id + ", Removing it");

                        if (!PlotManager.isPlotAvailable(id, w)) {
                            //PlotManager.getPlots(w).remove(id);
                            //TODO: REMOVE PLOT!! OR RELOAD PLOTME!
                        }
              
                        Location bottom = PlotManager.getPlotBottomLoc(w, id);
                        Location top = PlotManager.getPlotTopLoc(w, id);
                        PlotManager.clear(bottom, top);
                        
                        PlotManager.removeOwnerSign(w, id);
                        PlotManager.removeSellSign(w, id);

                        SqlManager.deletePlot(PlotManager.getIdX(id), PlotManager.getIdZ(id), w.getName().toLowerCase());
                        
                        
                    }
                    
                    if (plot.isAllowed(playerName)) {
                        plot.removeAllowed(playerName);
                    }
                }
                
            }
        }
    }
}
