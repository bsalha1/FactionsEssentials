package org.GladiusPvP.factionsessentials.cmd;

import org.GladiusPvP.factionsessentials.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdWild implements CommandExecutor
{
	private Location location;
	private Main main;
	
	public CmdWild(Main main)
	{
		this.main = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("Only players may execute this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		int range = main.getConfig().getInt("wild_radius");
		
		player.teleport(getRandLocation(range, player).add(0, 1, 0));
		player.sendMessage("§eYou have been teleported to a randomized location§f.");

		return true;
	}
	
	private Location getRandLocation(int range, Player player)
	{
		int randX = (int) (range * Math.random());   
		int randZ = (int) (range * Math.random());
		int randY = player.getWorld().getHighestBlockAt(randX, randZ).getY();
		
		location = new Location(player.getWorld(), randX, randY, randZ);
		
		if (location.add(0, -1, 0).getBlock().isLiquid())
		{
			getRandLocation(range, player);
		}
		
		return location;
		
	}
}
