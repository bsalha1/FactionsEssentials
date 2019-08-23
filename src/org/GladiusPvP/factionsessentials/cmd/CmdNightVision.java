package org.GladiusPvP.factionsessentials.cmd;

import org.GladiusPvP.factionsessentials.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CmdNightVision implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//
		// Player Validation
		//
		if (Utils.msgNeedPlayer(sender)) return true;
		Player player = (Player) sender;
		
		//
		// Statements
		//
		
		// If player doesn't have permission, exit
		if (!player.hasPermission("factionsadditions.nightvision") && !player.hasPermission("factionsadditions.*"))
		{
			Utils.msgPerm(player);
			return true;
		}
	
		// If player doesn't have night vision, add it
		if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
			Utils.msgYellow(player, "You have been granted night vision");	
		}
		// Otherwise, take the night vision away
		else
		{
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			Utils.msgYellow(player, "Your night vision has been removed");
		}
		
		return true;
	}
}
