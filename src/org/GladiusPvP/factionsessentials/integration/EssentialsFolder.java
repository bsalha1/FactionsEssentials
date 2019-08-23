package org.GladiusPvP.factionsessentials.integration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class EssentialsFolder 
{	
	public static FileConfiguration openUUIDConfig(String UUID)
	{
		File file = new File("plugins\\Essentials\\userdata\\" + UUID + ".yml");
		
		return YamlConfiguration.loadConfiguration(file);
	}
}
