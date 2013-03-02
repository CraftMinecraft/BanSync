package net.craftminecraft.bukkit.bansync;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.craftminecraft.bukkit.bansync.command.CommandManager;
import net.craftminecraft.bukkit.bansync.command.commands.DefaultCommand;
import net.craftminecraft.bukkit.bansync.command.commands.HelpCommand;
import net.craftminecraft.bukkit.bansync.command.commands.RemoveUserCommand;
import net.craftminecraft.bukkit.bansync.config.MainConfig;
import net.craftminecraft.bukkit.bansync.log.LogLevels;
import net.craftminecraft.bukkit.bansync.log.Logger;
import net.craftminecraft.bukkit.bansync.plugins.EssentialsHook;
import net.craftminecraft.bukkit.bansync.plugins.GriefPreventionHook;
import net.craftminecraft.bukkit.bansync.plugins.LWCPluginHook;
import net.craftminecraft.bukkit.bansync.plugins.PlotMePluginHook;
import net.craftminecraft.bukkit.bansync.plugins.VaultPluginHook;
import net.craftminecraft.bukkit.bansync.plugins.WorldGuardHook;

public class BanSync extends JavaPlugin implements Listener {
	public Logger logger = new Logger(this);
	private LWCPluginHook lwcplugin;
	private PlotMePluginHook plotmeplugin;
	private VaultPluginHook vaultplugin;
	private CommandManager commandManager;
	private GriefPreventionHook griefpreventionplugin;
	private WorldGuardHook worldguardplugin;
	private EssentialsHook essentialsplugin;
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
        
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        	logger.log(LogLevels.FATAL, "Failed to submit stats");
        }
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
    	
    	if (mainConfig.AutoDeletePlayerOnBan)
    	{
    		if (PlayerBanned)
    		{
    			clearPlayer(kickedplayer.getName());
    		}
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
		
		// Delete WorldGuard Regions
		if (mainConfig.EnableWorldGuard)
		{
			if (worldguardplugin.isHooked())
				worldguardplugin.ClearWorldGuardRegions(playerName);
		}
		
		// Delete Essentials Data
		if (mainConfig.EnableEssentials)
		{
			if (essentialsplugin.isHooked())
				essentialsplugin.ClearEssentials(playerName);
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
    	
    	// Hook WorldGuard
    	if (mainConfig.EnableWorldGuard)
    	{
    		worldguardplugin = new WorldGuardHook(this);
    		worldguardplugin.HookWorldGuard();
    	}
    	
    	// Hook Essentials
    	if (mainConfig.EnableEssentials)
    	{
    		essentialsplugin = new EssentialsHook(this);
    		essentialsplugin.HookEssentials();
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

