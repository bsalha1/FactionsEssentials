package org.GladiusPvP.factionsessentials.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.GladiusPvP.factionsessentials.Utils;
import org.GladiusPvP.factionsessentials.Main;

public class ConfigMain 
{
	private Main main;
	
	public ConfigMain(Main plugin)
	{
		main = plugin;
		
		loadDefaults();
	}
	
	private void loadDefaults()
	{
		Map<String, Object> defaults = new LinkedHashMap<>();
		
		Utils.configHeader(main.getConfig(), "Factions Essentials", "This file allows the configuration of various features.");
		defaults.put("blaze_pay", 50);
		defaults.put("wild_radius", 5000);
		defaults.put("tnt_fill_radius", 50);
		
		main.getConfig().addDefaults(defaults);
		main.getConfig().options().copyDefaults(true);
	}
}
