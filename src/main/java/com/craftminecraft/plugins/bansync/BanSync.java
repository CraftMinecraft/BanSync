package com.craftminecraft.plugins.bansync;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.craftminecraft.plugins.bansync.command.CommandManager;
import com.craftminecraft.plugins.bansync.command.commands.DefaultCommand;
import com.craftminecraft.plugins.bansync.command.commands.HelpCommand;
import com.craftminecraft.plugins.bansync.command.commands.RemoveUserCommand;
import com.craftminecraft.plugins.bansync.config.MainConfig;
import com.craftminecraft.plugins.bansync.log.Logger;
import com.craftminecraft.plugins.bansync.plugins.GriefPreventionHook;
import com.craftminecraft.plugins.bansync.plugins.LWCPluginHook;
import com.craftminecraft.plugins.bansync.plugins.PlotMePluginHook;
import com.craftminecraft.plugins.bansync.plugins.VaultPluginHook;

public class BanSync extends JavaPlugin implements Listener {
	public Logger logger = new Logger(this);
	private LWCPluginHook lwcplugin;
	private PlotMePluginHook plotmeplugin;
	private VaultPluginHook vaultplugin;
	private CommandManager commandManager;
	private GriefPreventionHook griefpreventionplugin;
	private MainConfig mainConfig;
	
    public void onDisable() {
        lwcplugin = null;
        plotmeplugin = null;
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        loadConfig();
        hookPlugins();
        registerCommands();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandManager.dispatch(sender, command, label, args, this);
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
    		clearPlayer(kickedplayer.getName());
    	}
    }
    
    public void clearPlayer(String playerName) {
		// Clear LWC Locks
    	if (mainConfig.EnableLWC)
    	{
    		if (lwcplugin.isHooked())
    			lwcplugin.ClearLWCLocks(playerName);
    	}
    	
		// Delete PlotMe Plots
		if (mainConfig.EnablePlotMe)
		{
			if (plotmeplugin.isHooked())
				plotmeplugin.ClearPlotMePlots(playerName);
		}
		
		// Delete Players Economy
		if (mainConfig.EnableVault)
		{
			if (vaultplugin.isHooked())
				vaultplugin.ClearEconomy(playerName);
		}
		
		// Delete Grief Prevention
		if (mainConfig.EnableGriefPrevention)
		{
			if (griefpreventionplugin.isHooked())
				griefpreventionplugin.ClearGriefPreventionLocks(playerName);
		}
    }
    
    private void hookPlugins() {
    	// Hook LWC
    	if (mainConfig.EnableLWC)
    	{
    		lwcplugin = new LWCPluginHook(this);
    		lwcplugin.HookLWC();
    	}
    	
    	// Hook PlotMe
    	if (mainConfig.EnablePlotMe)
    	{
    		plotmeplugin = new PlotMePluginHook(this);
    		plotmeplugin.HookPlotMe();
    	}
    	
    	// Hook Econony
    	if (mainConfig.EnableVault)
    	{
    		vaultplugin = new VaultPluginHook(this);
    		vaultplugin.HookVault();
    	}
    	
    	// Hook GriefPrevention
    	if (mainConfig.EnableGriefPrevention)
    	{
    		griefpreventionplugin = new GriefPreventionHook(this);
    		griefpreventionplugin.HookGriefPrevention();
    	}
    }
    
    private void registerCommands() {
    	commandManager = new CommandManager();
        // Load Commands
    	commandManager.addCommand(new DefaultCommand(this));
    	commandManager.addCommand(new HelpCommand(this));
        commandManager.addCommand(new RemoveUserCommand(this));
    }
    
    private void loadConfig()
    {
        mainConfig = new MainConfig(this);
        mainConfig.load();	
    }
    
    public String getTag() {
		String tag = ChatColor.GREEN+"[BanSync] "+ChatColor.AQUA;
		return tag;
	}
}

