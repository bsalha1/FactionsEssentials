package org.GladiusPvP.factionsessentials;

import org.GladiusPvP.factionsessentials.cmd.CmdChargedCreeper;
import org.GladiusPvP.factionsessentials.cmd.CmdConfigReload;
import org.GladiusPvP.factionsessentials.cmd.CmdFtnt;
import org.GladiusPvP.factionsessentials.cmd.CmdNightVision;
import org.GladiusPvP.factionsessentials.cmd.CmdWild;
import org.GladiusPvP.factionsessentials.config.ConfigMain;
import org.GladiusPvP.factionsessentials.config.ConfigTnt;
import org.GladiusPvP.factionsessentials.listener.ListenBlaze;
import org.GladiusPvP.factionsessentials.listener.ListenChargedCreeper;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@SuppressWarnings("unused")
	private ConfigMain configMain;
	private ConfigTnt configTnt;
	
	public void onEnable()
	{
		loadConfigs();
		
		loadCommands();
		
		loadListeners();
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "FactionsEssentials enabled");
	}
	
	public void onDisable()
	{
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "FactionsEssentials disabled");
	}
	
	private void loadCommands()
	{
		getCommand("chargedcreeperegg").setExecutor(new CmdChargedCreeper());
		getCommand("nightvision").setExecutor(new CmdNightVision());
		getCommand("wild").setExecutor(new CmdWild(this));
		getCommand("fereload").setExecutor(new CmdConfigReload(this));
		getCommand("ftnt").setExecutor(new CmdFtnt(this));
	}
	
	private void loadListeners()
	{
		PluginManager pluginManager = this.getServer().getPluginManager();
		
		pluginManager.registerEvents(new ListenBlaze(), this);
		pluginManager.registerEvents(new ListenChargedCreeper(), this);
	}
	
	private void loadConfigs()
	{
		this.configMain = new ConfigMain(this);
		this.saveConfig();
		this.reloadConfig();
		
		this.configTnt = new ConfigTnt(this);
		this.configTnt.save();
		this.configTnt.reload();
	}
	
	public ConfigTnt getConfigTnt()
	{
		return this.configTnt;
	}
}
