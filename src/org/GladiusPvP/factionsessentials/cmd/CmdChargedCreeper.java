package org.GladiusPvP.factionsessentials.cmd;

import org.GladiusPvP.factionsessentials.Utils;
import org.GladiusPvP.factionsessentials.item.ChargedCreeperEgg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdChargedCreeper implements CommandExecutor
{	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//
		// Player Validation
		//
		if (Utils.msgNeedPlayer(sender)) return true;
		Player player = (Player) sender;
		if (Utils.msgNeedOp(player)) return true;
		
		//
		// Local Declarations
		//
		ItemStack item = new ChargedCreeperEgg().getItem();
		
		//
		// Statements
		//
		item.setAmount(64); // Set amount of items to 64
		player.getInventory().addItem(item); // Give player the items
		
		return true;
	}

}
