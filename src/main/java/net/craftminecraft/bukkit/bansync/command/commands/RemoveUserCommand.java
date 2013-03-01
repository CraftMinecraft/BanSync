package net.craftminecraft.bukkit.bansync.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import net.craftminecraft.bukkit.bansync.BanSync;
import net.craftminecraft.bukkit.bansync.command.BaseCommand;
import net.craftminecraft.bukkit.bansync.log.LogLevels;

public class RemoveUserCommand extends BaseCommand{
	public RemoveUserCommand(BanSync plugin) {
		super(plugin);
		name = "RemoveUser";
		description = "Manually removes all users properties and locks";
		usage = "/bansync RemoveUser <player>";
		minArgs = 1;
		maxArgs = 1;
		identifiers.add("bansync removeuser");
	}
	
	@Override
    public void execute(CommandSender sender, String[] args) {	
		String playerName = args[0];
		
		if (!sender.hasPermission("bansync.removeuser"))
    	{
			plugin.logger.log(LogLevels.INFO, sender.getName() + " send command /bansync removeuser " + playerName + " and was denied.");
    		sender.sendMessage(plugin.getTag() + ChatColor.RED + "You do not have permission to use this command");
    		return;
    	}
		
		// Delete Stuff
		plugin.logger.log(LogLevels.INFO, sender.getName() + " send command /bansync removeuser " + playerName);
		
		plugin.clearPlayer(playerName);
		
		sender.sendMessage(plugin.getTag() + playerName + " has been cleared");
	}
}