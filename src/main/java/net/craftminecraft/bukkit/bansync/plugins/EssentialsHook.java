package net.craftminecraft.bukkit.bansync.plugins;

import org.bukkit.plugin.Plugin;

import net.craftminecraft.bukkit.bansync.BanSync;
import net.craftminecraft.bukkit.bansync.log.LogLevels;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class EssentialsHook {
	private BanSync bansyncinterface = null;
	private Boolean pluginHooked = false;
	public static Essentials ess = null;
	
	public EssentialsHook (BanSync p) {
		bansyncinterface = p;
	}
	
	public Boolean isHooked()
	{
		return pluginHooked;
	}
	
	public Boolean HookEssentials()
	{
		Plugin p = bansyncinterface.getServer().getPluginManager().getPlugin("Essentials");
		if (p != null && p instanceof Essentials) {
			pluginHooked = true;
			bansyncinterface.logger.log(LogLevels.INFO, "Essentials Found, hooking into Essentials.");
			ess = (Essentials) p;
			return true;
		} else {
			pluginHooked = false;
			bansyncinterface.logger.log(LogLevels.INFO, "Essentials not Found");
			return false;
		}
	}
	
    public void ClearEssentials(String playerName) {
        User player = null;

        try {
            player = ess.getOfflineUser(playerName);
            player.reset();
        }
        catch (Exception ex) {
            
        }
    }
}
