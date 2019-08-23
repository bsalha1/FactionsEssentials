package org.GladiusPvP.factionsessentials;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class Utils 
{
	//
	// Coding Titles
	//
	public static void configHeader(FileConfiguration config, String plugin, String description)
	{
		String header = 
				"\n" + "Author: ReachCarter" +
				"\n" + "License: All Rights Reserved" +
				"\n" +
				"\n" + "Plugin: " + plugin +
				"\n" + "Description: " + description +
				"\n" +
				"\n" + "Email: bsalha1@gmail.com" +
				"\n";
		
		config.options().header(header);
	}
	
	//
	// Inventory Tools
	//
	public static void addInventoryFrame(Inventory inventory, int size)
	{
		//
		// Local Declarations
		//
		ItemStack frame = new ItemStack(Material.STAINED_GLASS_PANE); // The frame item
		ItemMeta frameIM = frame.getItemMeta(); // The item meta/data of the frame
		
		//
		// Frame Properties
		//
		frameIM.setDisplayName(" "); // Blank frame name for cleanliness
		frame.setItemMeta(frameIM);  // Set item frame display name
		
		//
		// Add Frame
		//
		for (int i = 0; i < size; i++) if (inventory.getItem(i) == null) inventory.setItem(i, frame);
	}
	
	//
	// Messaging Tools
	//
	public static String period = ChatColor.WHITE + "." ;
	
	public static boolean msgNeedPlayer(CommandSender sender)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Only players may execute this command.");
			return true;
		}
		
		return false;
	}
	
	public static void msgYellow(Player player, String msg)
	{
		player.sendMessage(ChatColor.YELLOW + msg + Utils.period);
	}
	
	public static void msgRed(Player player, String msg)
	{
		player.sendMessage(ChatColor.RED + msg + Utils.period);
	}

	public static void msgPerm(Player player)
	{
		player.sendMessage(ChatColor.RED + "Invalid permissions" + Utils.period);
	}
	
	public static boolean msgNeedOp(Player player)
	{
		if(!player.isOp())
		{
			player.sendMessage(ChatColor.RED + "Only operators can use this command" + Utils.period);
			
			return true;
		}
		
		return false;
	}
	
	//
	// Event Fix Tools
	//
	public static Location fixMobSpawn(Location spawnLocation, BlockFace blockFace)
	{
		switch (blockFace)
		{
		case NORTH:
			spawnLocation.add(0.5, 0, -0.5);
			break;
			
		case SOUTH:
			spawnLocation.add(0.5, 0, 1.5);	
			break;
			
		case WEST:
			spawnLocation.add(-0.5, 0, 0.5);
			break;
			
		case EAST:
			spawnLocation.add(1.5, 0, 0.5);
			break;
			
		case UP:
			spawnLocation.add(0.5, 1, 0.5);
			break;
			
		default:
			spawnLocation.add(0.5, -1, 0.5);
			break;
		}
		
		return spawnLocation;
	}
	
	public static Location fixBlockPlace(Location spawnLocation, BlockFace blockFace)
	{
		switch (blockFace)
		{
		case NORTH:
			spawnLocation.add(0, 0, -1);
			break;
			
		case SOUTH:
			spawnLocation.add(0, 0, 1);	
			break;
			
		case WEST:
			spawnLocation.add(-1, 0, 0);
			break;
			
		case EAST:
			spawnLocation.add(1, 0, 0);
			break;
			
		case UP:
			spawnLocation.add(0, 1, 0);
			break;
			
		default:
			spawnLocation.add(0, -1, 0);
			break;
		}
		
		return spawnLocation;
	}

	
}
