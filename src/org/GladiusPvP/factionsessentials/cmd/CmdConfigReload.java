package org.GladiusPvP.factionsessentials.cmd;

import org.GladiusPvP.factionsessentials.Main;
import org.GladiusPvP.factionsessentials.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdConfigReload implements CommandExecutor
{
	private Main main;
	
	public CmdConfigReload(Main plugin)
	{
		main = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[])
	{
		main.reloadConfig();
		
		sender.sendMessage(ChatColor.GREEN + "The FactionsEssentials config has been reloaded" + Utils.period);
		
		return true;
	}
}
