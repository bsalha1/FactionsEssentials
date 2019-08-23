package org.GladiusPvP.factionsessentials.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomSpawner 
{
	//
	// Class Declarations
	//
	private ItemStack customSpawner; // The handheld item
	private static EntityType mob = EntityType.UNKNOWN; // Type of mob, initialized as UNKNOWN
	
	@SuppressWarnings("deprecation")
	public CustomSpawner()
	{	
		//
		// Local Declarations
		//
		ItemStack item = new ItemStack(Material.MOB_SPAWNER); // Set handheld item to spawner
		ItemMeta itemMeta = item.getItemMeta(); // Get the item meta/data of the spawner
				
		//
		// Materalize Type
		//
		itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + mob.getName() + ChatColor.WHITE + " Spawner"); // Set the spawner's name
		itemMeta.addEnchant(Enchantment.DURABILITY, mob.getTypeId(), true); // Set the spawners proper enchantment level
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); // Hide the enchantment
		
		//
		// Setting
		//
		item.setItemMeta(itemMeta); // Give the spawner the item meta/data
		customSpawner = item; // Put item in customSpawner for getter
	}
	
	public ItemStack getItem()
	{
		return customSpawner;
	}
	
	public CustomSpawner setMob(EntityType mob)
	{
		CustomSpawner.mob = mob;
		
		return new CustomSpawner();
	}
	
}
