package org.GladiusPvP.factionsessentials.item;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shockwave
{
    private static int dimension = 3;
    private ItemStack shockwave;
    private static String name = ChatColor.AQUA + "" + ChatColor.BOLD + "Shockwave " + ChatColor.BLUE + "" + ChatColor.BOLD + "Pickaxe";
    public static String description = ChatColor.YELLOW + "Shockwave Pickaxe";

    public Shockwave()
    {
        //
        // Local Declarations
        //
        String lore1 = ChatColor.GREEN + "" + ChatColor.BOLD + dimension + "x" + dimension;
        ArrayList<String> loreList = new ArrayList<>(); // Item Lore
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE); // Set as diamond pickaxe
        ItemMeta itemMeta = item.getItemMeta(); // Item meta

        //
        // Set Text Characteristics
        //
        loreList.add(description);
        loreList.add(lore1);
        itemMeta.setLore(loreList);
        itemMeta.setDisplayName(name);
        itemMeta.addEnchant(Enchantment.DIG_SPEED, 8, true);
        item.setItemMeta(itemMeta);

        //
        // Setters
        //
        shockwave = item;
    }

    public Shockwave setDimension(int dim)
    {
        // If the dimension is put as even or less than or equal to 0...
        if (dim % 2 == 0 || dim <= 0)
        {
            return null;
        }
        else
        {
            dimension = dim;
        }

        return new Shockwave();
    }

    public ItemStack getItem()
    {
        return shockwave;
    }
}
