package org.GladiusPvP.factionsessentials.config;

import org.GladiusPvP.factionsessentials.Main;
import org.GladiusPvP.factionsessentials.abstracts.AbstractConfig;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigTnt extends AbstractConfig
{
	public ConfigTnt(Main main)
	{
		super(main, "tnt.yml");
		setDefaults();
	}
	
	public FileConfiguration getConfig()
	{
		return this.config;
	}
	
	public void setDefaults()
	{
		
	}
}
