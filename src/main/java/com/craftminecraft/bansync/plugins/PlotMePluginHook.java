package com.craftminecraft.bansync.plugins;

import com.craftminecraft.bansync.BanSync;
import com.worldcretornica.plotme.PlotMe;


public class PlotMePluginHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	private PlotMe plotmeplugininterface;
	
	public PlotMePluginHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	
}
