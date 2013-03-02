package net.craftminecraft.bukkit.bansync.command.commands;

import org.bukkit.command.CommandSender;

import net.craftminecraft.bukkit.bansync.BanSync;
import net.craftminecraft.bukkit.bansync.command.BaseCommand;

public class DefaultCommand extends BaseCommand{
	public DefaultCommand(BanSync plugin) {
		super(plugin);
		name = "Default";
		description = "Default command";
		usage = "/bansync";
		minArgs = 0;
		maxArgs = 0;
		identifiers.add("bansync");
	}
	
	@Override
    public void execute(CommandSender sender, String[] args) {	
		String pluginVersion = plugin.getDescription().getVersion();
		
		sender.sendMessage(plugin.getTag() + "Bansync Version " + pluginVersion);
		sender.sendMessage(plugin.getTag() + "Type /bansync help for help");
	}
}
