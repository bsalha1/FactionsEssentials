package org.GladiusPvP.factionsessentials.listener;

import org.GladiusPvP.factionsessentials.integration.EssentialsEconomy;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

public class ListenBlaze implements Listener
{
	private Player player;
	
	@EventHandler
	public void onBlazeDmg(EntityDamageEvent event)
	{
		if (event.getEntityType() != EntityType.BLAZE) return;
		
		else if (event.getCause() == DamageCause.DROWNING) 
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlazeHit(EntityDamageByEntityEvent event)
	{
		if ((event.getEntityType() != EntityType.BLAZE) || !(event.getDamager() instanceof Player)) return;
		
		event.setDamage(20);
		
		player = (Player) event.getDamager();
	}
	
	@EventHandler
	public void onBlazeSpawn(EntityDeathEvent event)
	{
		if (event.getEntityType() != EntityType.BLAZE) return;
		
		event.getDrops().clear();
		
		EssentialsEconomy.giveMoney(player, 50);
	}
}
