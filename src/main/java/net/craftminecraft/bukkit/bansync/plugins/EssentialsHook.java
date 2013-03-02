package net.craftminecraft.bukkit.bansync.plugins;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
	
	public void ClearEssentials(String playerName)
	{
		User user;
		
		Player p = Bukkit.getPlayer(playerName);
		if (p != null) {
			user = ess.getUser(p);
		} else {
			user = ess.getOfflineUser(playerName);
		}
		
		List<String> homes = ess.getUser(user).getHomes();
		
		bansyncinterface.logger.log(LogLevels.INFO, "Player has " + homes.size() + " home(s)");
	
		for(Iterator<String> i = homes.iterator(); i.hasNext(); )
		{
			String home = i.next();
			try {
				ess.getUser(user).delHome(home);
			} catch (Exception e) {
				bansyncinterface.logger.log(LogLevels.FATAL, e.getMessage());
			}
		}
		
		ess.getUser(user).remove();
	}
}
