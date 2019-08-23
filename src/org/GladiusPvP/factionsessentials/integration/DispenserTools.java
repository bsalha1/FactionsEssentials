package org.GladiusPvP.factionsessentials.integration;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DispenserTools 
{
	public static void fill(Player player, int radius, int amount)
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + player.getName() + " dt " + radius + " tnt " + amount);
	}
}
