package org.GladiusPvP.factionsessentials.integration;

import org.GladiusPvP.factionsessentials.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EssentialsEconomy 
{
	public static boolean takeMoney(Player player, double money)
	{	
		//
		// If the player's balance is below the cost, not enough money
		//
		if (getBal(player) < money)
		{
			Utils.msgRed(player, "Not enough money");
			return false;
		}
		//
		// Otherwise, take the money and give the items
		//
		else
		{
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " " + money);
			return true;
		}
	}
	
	public static double getBal(Player player)
	{
		FileConfiguration config = EssentialsFolder.openUUIDConfig(player.getUniqueId().toString());
		
		String money = config.getString("money");
		
		return Double.parseDouble(money);
	}
	
	public static void giveMoney(Player player, int money)
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + money);
	}
}
