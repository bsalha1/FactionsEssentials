package org.GladiusPvP.factionsessentials.abstracts;

import java.io.File;
import java.io.IOException;

import org.GladiusPvP.factionsessentials.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class AbstractConfig 
{
	protected Main main;
	private File file;
	protected FileConfiguration config;
	
	public AbstractConfig(Main main, String fileName)
	{
		this.main = main;
		this.file = new File(main.getDataFolder(), fileName);
		
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save()
	{
		try
		{
			config.save(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void reload()
	{
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	

	
	
}