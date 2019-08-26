package org.GladiusPvP.factionsessentials.listener;

import org.GladiusPvP.factionsessentials.item.Shockwave;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ListenShockwave implements Listener
{
    @EventHandler
    public void onPickaxeBreak(BlockBreakEvent breakEvent)
    {
        //
        // Local Declarations
        //
        Player player = breakEvent.getPlayer();

        try
        {
            // If the item in hand has the same first line as a shockwave, exit
            if (!player.getInventory().getItemInHand().getItemMeta().getLore().get(0).equals(Shockwave.description) || player.isSneaking())
            {
                return;
            }
        }
        catch (NullPointerException e)
        {
            return;
        }

        //
        // Local Declarations
        //
        int dimension = Character.getNumericValue(player.getInventory().getItemInHand().getItemMeta().getLore().get(1).charAt(4)); // The dimension of shockwave

        // Dig area...
        dig(breakEvent.getBlock().getLocation(), dimension);
    }

    //
    // Digs the Specified Dimension
    //
    private void dig(Location location, int dimension)
    {
        //
        // Local Declarations
        //
        int x = location.getBlockX(); // X coordinate
        int y = location.getBlockY(); // Y coordinate
        int z = location.getBlockZ(); // Z coordinate
        int h = dimension / 2; // Range to dig

        for(int k = z - h; k <= z + h; k++)
        {
            for(int j = y - h; j <= y + h; j++)
            {
                for(int i = x - h; i <= x + h; i++)
                {
                    location.setX(i);
                    location.setY(j);
                    location.setZ(k);

                    if (!(location.getBlock().getType() == Material.BEDROCK))
                    {
                        location.getBlock().setType(Material.AIR);
                    }
                }
            }
        }
    }
}
