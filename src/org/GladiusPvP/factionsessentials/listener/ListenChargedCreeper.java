package org.GladiusPvP.factionsessentials.listener;

import org.GladiusPvP.factionsessentials.Utils;
import org.GladiusPvP.factionsessentials.item.ChargedCreeperEgg;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ListenChargedCreeper implements Listener
{
	@EventHandler	
	public void onCreeperPlace(PlayerInteractEvent event)
	{	
		Player player = event.getPlayer();
		
		try
		{
			if (!player.getInventory().getItemInHand().getItemMeta().getLore().get(0).equals(ChargedCreeperEgg.description) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) return;		
		}
		catch (NullPointerException e)
		{
			return;
		}
		
		Location spawnLocation = event.getClickedBlock().getLocation();
			
		Utils.fixMobSpawn(spawnLocation, event.getBlockFace());
		
		Entity spawnMob = event.getClickedBlock().getWorld().spawnEntity(spawnLocation, EntityType.CREEPER);
		Creeper creeper = (Creeper) spawnMob;
		
		creeper.setPowered(true);
	}
}
