package org.GladiusPvP.factionsessentials.cmd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.GladiusPvP.factionsessentials.Main;
import org.GladiusPvP.factionsessentials.integration.FactionsFolder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdFtnt implements CommandExecutor
{
	// Class Declarations
	private Main main; // The plugin
	private FileConfiguration configTnt; // tnt.yml
	private String factionID; // ID of player's faction
	private int tntBal;       // Amount of tnt owned by player's faction
	private Player player;    // Player executing command
	
	public CmdFtnt(Main main)
	{
		this.main = main;
		configTnt = main.getConfigTnt().getConfig();
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  onCommand                                       
	|                                                                              
	|  Description:    When player types command "/ftnt", onCommand is called.                                                                                                              
	|                                                                              
	|  Parameter:      sender, cmd, label, args         
	|                                                                              
	|  Return Value:   boolean              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Only players may execute this command.");
			return true;
		}
		
		player = (Player) sender;
		factionID = FactionsFolder.getFactionID(player);
		tntBal = configTnt.getInt(factionID);
		
		// If the config doesn't contain the player's faction, add it
		if (!(configTnt.contains(factionID)))
		{
			configTnt.set(factionID, 0);
		}
		
		// Parse through arguments to deliver specified action
		handleArguments(args);
		
		// Save the tnt.yml config
		main.getConfigTnt().save();
		
		return true;
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  handleArguments                                       
	|                                                                              
	|  Description:    Parses through args to deliver specified actions.                                                                                                              
	|                                                                              
	|  Parameter:      args         
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void handleArguments(String[] args)
	{
		// Local Declarations //
		int amount; // Amount of TNT to transfer
		
		switch(args.length)
		{
			// If no arguments entered...
			case 0:
				player.sendMessage("§eYour faction has §6" + configTnt.getInt(factionID) + " §etnt§f.");
				break;
				
			// If 1 argument entered...
			case 1:
				if(args[0].equalsIgnoreCase("addall"))
				{
					addAllTnt();
				}
				else
				{
					argErrorMsg();
				}
				break;
				
			// If 2 arguments entered...
			case 2:
				try
				{
					amount = Integer.parseInt(args[1]);
				}
				catch(NumberFormatException e)
				{
					player.sendMessage("§cInvalid amount§f; §cmust enter an integer§f."); 
					return;
				}
				
				// If the amount is less than or equal to zero, the action is not defined...
				if (amount <= 0)
				{
					player.sendMessage("§cAmount of tnt must be greater than 0§f.");
					return;
				}
				
				// If argument is "____"...
				if(args[0].equalsIgnoreCase("add"))
				{
					addTnt(amount);
				}
				else if(args[0].equalsIgnoreCase("take"))
				{
					takeTnt(amount);
				}
				else if(args[0].equalsIgnoreCase("fill"))
				{
					fillTnt(amount, main.getConfig().getInt("tnt_fill_radius"));
				}
				else
				{
					argErrorMsg();
				}
				break;
				
			// If > 2 arguments entered...
			default:
				argErrorMsg();
		}
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  argErrorMsg                                       
	|                                                                              
	|  Description:    Sends player an informative argument error message.                                                                                                             
	|                                                                              
	|  Parameter:               
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void argErrorMsg() 
	{

		player.sendMessage("§cInvalid argument(s)§f; §c/ftnt <add/addall/take/fill> <amount>");
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  addTnt                                       
	|                                                                              
	|  Description:    Takes amount of TNT from player's inventory and deposits it
	|                  into faction's TNT balance.                                                                                                              
	|                                                                              
	|  Parameter:      amount         
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void addTnt(int amount)
	{
		// Local Declarations
		int inventoryTnt = 0; // Amount of tnt in player inventory
		
		// Run through player inventory to look for tnt...
		for(ItemStack item : player.getInventory().getContents())
		{
			if(item != null && item.getType().equals(Material.TNT))
			{
				inventoryTnt += item.getAmount();
			}
		}
		
		// If the player doesn't have enough tnt to deposit amount...
		if (inventoryTnt < amount)
		{
			player.sendMessage("§cYou do not have §4" + amount + " §ctnt§f.");
		}
		else
		{
			configTnt.set(factionID, tntBal + amount);
			player.getInventory().remove(Material.TNT);
			player.sendMessage("§aYou have deposited §2" + amount + " §atnt§f.");
		}
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  addAllTnt                                       
	|                                                                              
	|  Description:    Takes all TNT from player's inventory and deposits into 
	|                  faction's TNT balance.                                                                                                              
	|                                                                              
	|  Parameter:               
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void addAllTnt()
	{
		// Local Declarations //
		int inventoryTnt = 0; // Amount of TNT in player inventory
		
		// Parse through player's inventory for TNT
		for(ItemStack item : player.getInventory().getContents())
		{
			if(item != null && item.getType().equals(Material.TNT))
			{
				inventoryTnt += item.getAmount();
			}
		}
		
		if(inventoryTnt == 0)
		{
			player.sendMessage("§cYou have no tnt§f.");
		}
		else
		{
			addTnt(inventoryTnt);
		}
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  takeTnt                                       
	|                                                                              
	|  Description:    Gives player amount of TNT from their faction's TNT balance.                                                                                                              
	|                                                                              
	|  Parameter:      amount         
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void takeTnt(int amount)
	{
		// Local Declarations
		int space = 0; // Space in player's inventory for TNT
		
		// Run through player's inventory to find number of empty slots
		for(ItemStack i : player.getInventory().getContents())
		{
			if(i == null)
			{
				space += 64;
			}
			else if(i.getType().equals(Material.TNT))
			{
				space += 64 - i.getAmount();
			}
		}
		
		// If the faction doesn't have enough tnt...
		if (tntBal < amount)
		{
			player.sendMessage("§cYour faction does not have §4" + amount + " §ctnt§f.");
		}
		// If the player's inventory is smaller than the amount requested...
		else if(amount > space)
		{
			player.sendMessage("§cYour inventory can only hold §4" + space + " §ctnt§f.");
		}
		else
		{
			// Subtract TNT from faction TNT balance, add to inventory and send message to player
			configTnt.set(factionID, configTnt.getInt(factionID) - amount);
			player.getInventory().addItem(new ItemStack(Material.TNT, amount));
			player.sendMessage("§aYou have withdrawn §2" + amount + " §atnt§f.");
		}
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  fillTnt                                   
	|                                                                              
	|  Description:    Fills each dispenser in radius with amount of TNT.                                                                                                              
	|                                                                              
	|  Parameter:      amount, radius         
	|                                                                              
	|  Return Value:   void              
	|                                                                              
	+-----------------------------------------------------------------------------*/
	private void fillTnt(int amount, int radius)
	{
		// Local Declarations //
		Location location = player.getLocation(); // Player location
		Dispenser dispenser; // A dispenser in radius
		int numTnt;          // Total number of tnt used to fill in region
		int space;           // How much space for filling tnt in dispenser 

		// List of dispensers in radius
		ArrayList<Dispenser> dispensers = findDispensers(location, radius);

		// If there are no dispensers in region...
		if(dispensers.size() == 0)
		{
			player.sendMessage("§cThere are no dispensers within a §4" + radius + " §cblock radius§f.");
			return;
		}
		
		Iterator<Dispenser> itr = dispensers.iterator();
		numTnt = 0;
		
		//
		// Find how much space for filling there is in each dispenser
		//
		
		// Iterate through list...
		while(itr.hasNext())
		{
			dispenser = itr.next();
			space = 0; // Assume the dispenser is full
			
			for(ItemStack is : dispenser.getInventory().getContents())
			{
				// If empty slot... there is space for 64 tnt
				if(is == null)
				{
					space += 64;
				}
				// If a slot already has TNT... count how much is left till it is a stack
				else if(is.getType().equals(Material.TNT))
				{
					space += 64 - is.getAmount();
				}
			}
			
			//
			// Fill the dispensers
			//
			
			// If the dispenser cannot store the full amount...
			if(space < amount)
			{
				dispenser.getInventory().addItem(new ItemStack(Material.TNT, space));
				numTnt += space; // Fill all space with tnt
			}
			// If the dispenser can store the full amount...
			else
			{
				dispenser.getInventory().addItem(new ItemStack(Material.TNT, amount));
				numTnt += amount; // Fill necessary spaces with tnt
			}
		}
	
		player.sendMessage(
				"§aYou have filled §2" + dispensers.size() + " §adispensers with §2" + amount + " §atnt each §f(§2" + numTnt + " total§f).");
	
		configTnt.set(factionID, tntBal - numTnt);
	}
	
	
	
	/*-----------------------------------------------------------------------------+
	|    F u n c t i o n
	+------------------------------------------------------------------------------+                                    
	|  Function Name:  findDispensers                                       
	|                                                                              
	|  Description:    Finds all dispensers at location in radius, returns list of
	|                  dispensers.                                                                                                             
	|                                                                              
	|  Parameter:      location, radius         
	|                                                                              
	|  Return Value:   List<Dispenser>              
	|                                                                              
	|
	+-----------------------------------------------------------------------------*/
	private ArrayList<Dispenser> findDispensers(Location location, int radius)
	{
		int xIni = (int) location.getX(); // X coordinate of player
		int yIni = (int) location.getY(); // Y coordinate of player
		int zIni = (int) location.getZ(); // Z coordinate of player
		ArrayList<Dispenser> dispensers = new ArrayList<>(); //  List of dispensers
		
		for(int x = xIni - radius - 1; x < xIni + radius; x++)
		{
			for(int y = yIni - radius; y < yIni + radius + 2; y++)
			{
				for(int z = zIni - radius; z < zIni + radius + 1; z++)
				{
					location.setX(x);
					location.setY(y);
					location.setZ(z);
					
					if(location.getBlock().getType().equals(Material.DISPENSER))
					{
						dispensers.add((Dispenser) location.getBlock().getState());
					}
				}
			}
		}
		
		return dispensers;
	}
}
