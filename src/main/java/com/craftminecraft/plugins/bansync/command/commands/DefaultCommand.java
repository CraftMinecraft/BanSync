package com.craftminecraft.plugins.bansync.command.commands;

import org.bukkit.command.CommandSender;

import com.craftminecraft.plugins.bansync.BanSync;
import com.craftminecraft.plugins.bansync.command.BaseCommand;

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
		sender.sendMessage(plugin.getTag() + "Bansync Version 0.06 - /bansync help for help");
	}
}
