package com.craftminecraft.bansync.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.craftminecraft.bansync.BanSync;
import com.craftminecraft.bansync.command.BaseCommand;

public class HelpCommand extends BaseCommand{
	public HelpCommand(BanSync plugin) {
		super(plugin);
		name = "Help";
		description = "Plugin Help";
		usage = "/bansync Help";
		minArgs = 0;
		maxArgs = 0;
		identifiers.add("bansync help");
	}
	
	@Override
    public void execute(CommandSender sender, String[] args) {	
		sender.sendMessage(plugin.getTag() + "Bansync Commands:");
		sender.sendMessage(ChatColor.AQUA + "/bansync removeuser <username>");
		sender.sendMessage(ChatColor.DARK_AQUA + "Manually Remove User");
	}
}
