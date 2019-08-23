package org.GladiusPvP.factionsessentials.item;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChargedCreeperEgg 
{
	private ItemStack chargedCreeperEgg;
	public static String description = ChatColor.YELLOW + "Place for a charged creeper";

	public ChargedCreeperEgg()
	{
		ArrayList<String> loreList = new ArrayList<>();
		ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, (byte) 1);
		ItemMeta itemMeta = item.getItemMeta();
		String displayName = ChatColor.AQUA + "" + ChatColor.BOLD + "Charged " + ChatColor.GREEN + "" + ChatColor.BOLD + "Creeper Egg";
		
		// Lore and Display Name
		loreList.add(description);
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore(loreList);
		item.setItemMeta(itemMeta);
		
		chargedCreeperEgg = item;
	}
	
	public ItemStack getItem()
	{	
		return chargedCreeperEgg;
	}
}
