package com.craftminecraft.bansync;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.craftminecraft.bansync.command.CommandManager;
import com.craftminecraft.bansync.command.commands.DefaultCommand;
import com.craftminecraft.bansync.command.commands.HelpCommand;
import com.craftminecraft.bansync.command.commands.RemoveUserCommand;
import com.craftminecraft.bansync.log.Logger;
import com.craftminecraft.bansync.plugins.LWCPluginHook;
import com.craftminecraft.bansync.plugins.PlotMePluginHook;
import com.craftminecraft.bansync.plugins.VaultPluginHook;

public class BanSync extends JavaPlugin implements Listener {
	public Logger logger = new Logger(this);
	private LWCPluginHook lwcplugin;
	private PlotMePluginHook plotmeplugin;
	private VaultPluginHook vaultplugin;
	private CommandManager commandManager;
	
    public void onDisable() {
        lwcplugin = null;
        plotmeplugin = null;
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
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
		if (lwcplugin.isHooked())
			lwcplugin.ClearLWCLocks(playerName);
		
		// Delete PlotMe Plots
		if (plotmeplugin.isHooked())
			plotmeplugin.ClearPlotMePlots(playerName);
		
		// Delete Players Economy
		if (vaultplugin.isHooked())
			vaultplugin.ClearEconomy(playerName);
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
    
    private void registerCommands() {
    	commandManager = new CommandManager();
        // Load Commands
    	commandManager.addCommand(new DefaultCommand(this));
    	commandManager.addCommand(new HelpCommand(this));
        commandManager.addCommand(new RemoveUserCommand(this));
    }
    
    public String getTag() {
		String tag = ChatColor.GREEN+"[BanSync] "+ChatColor.AQUA;
		return tag;
	}
}

