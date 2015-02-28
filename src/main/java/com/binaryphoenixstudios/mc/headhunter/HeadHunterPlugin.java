package com.binaryphoenixstudios.mc.headhunter;

import com.binaryphoenixstudios.mc.headhunter.command.ReloadCommand;
import com.binaryphoenixstudios.mc.headhunter.command.SpawnHeadCommand;
import com.binaryphoenixstudios.mc.headhunter.command.VersionCommand;
import com.binaryphoenixstudios.mc.headhunter.listener.BlockBreakListener;
import com.binaryphoenixstudios.mc.headhunter.listener.EntityDeathListener;
import com.binaryphoenixstudios.mc.headhunter.listener.PlayerInteractListener;
import com.binaryphoenixstudios.mc.headhunter.model.HeadConfig;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created by Scott on 2/21/2015.
 */
public class HeadHunterPlugin extends JavaPlugin
{
	public static final String PLUGIN_NAME = "HeadHunter";
	protected Map<Class, HeadConfig> headConfigs;
	protected Map<String, Class> headNames;
	protected List<String> disabledWorlds;
	protected boolean isLootingEnabled;
	protected boolean dropOnPlayerKillOnly;

	@Override
	public void onEnable()
	{
		saveDefaultConfig();
		saveConfigurationFiles();

		headConfigs = new HashMap<Class, HeadConfig>();
		headNames = new HashMap<String, Class>();
		loadConfiguration();

		registerListeners();
		registerCommands();

	}

	/**
	 * If the config folder doesn't exist, create it and populate it with the
	 */
	protected void saveConfigurationFiles()
	{
		File dataFolder = new File(getDataFolder() + File.separator + "config");
		if(!dataFolder.exists())
		{
			dataFolder.mkdir();
			try
			{
				InputStream stream = getClass().getResourceAsStream("/defaultConfiguration.properties");
				Properties properties = new Properties();
				properties.load(stream);

				for(Object valueObject : properties.values())
				{
					String value = (String) valueObject;
					InputStream readFile = getClass().getResourceAsStream("/configuration/" + value);
					File writeFile = new File(dataFolder + File.separator + value);
					FileUtils.copyInputStreamToFile(readFile, writeFile);
				}
			}
			catch(Exception e)
			{
				getLogger().log(Level.SEVERE, "Error saving default configuration", e);
			}

		}
	}

	protected void loadConfiguration()
	{
		File configFolder = new File(getDataFolder() + File.separator + "config");
		if(configFolder.isDirectory())
		{
			for(File file : configFolder.listFiles())
			{
				try
				{
					Properties config = new Properties();
					FileInputStream fis = new FileInputStream(file);
					config.load(fis);

					HeadConfig headConfig = new HeadConfig();

					headConfig.setEntityClass(config.getProperty(HeadConfig.PROPERTY_KEY_ENTITY_CLASS));
					headConfig.setMaterialName(config.getProperty(HeadConfig.PROPERTY_KEY_MATERIAL_NAME));
					headConfig.setHeadMetadata(Short.parseShort(config.getProperty(HeadConfig.PROPERTY_KEY_HEAD_METADATA)));
					headConfig.setPlayerName(config.getProperty(HeadConfig.PROPERTY_KEY_PLAYER_NAME));
					headConfig.setChance(Long.parseLong(config.getProperty(HeadConfig.PROPERTY_KEY_CHANCE)));
					headConfig.setDisplayName(config.getProperty(HeadConfig.PROPERTY_KEY_DISPLAY_NAME));

					headConfigs.put(Class.forName(headConfig.getEntityClass()), headConfig);
					headNames.put(headConfig.getPlayerName(), Class.forName(headConfig.getEntityClass()));
				}
				catch(Exception e)
				{
					getLogger().log(Level.SEVERE, "Error loading configuration" + file.getName(), e);
				}

			}
		}

		disabledWorlds = getConfig().getStringList("DisabledWorlds");
		isLootingEnabled = getConfig().getBoolean("ApplyLooting");
		dropOnPlayerKillOnly = getConfig().getBoolean("DropOnlyOnPlayerKill");
	}

	protected void registerListeners()
	{
		Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
	}

	protected void registerCommands()
	{
		getCommand("spawnhead").setExecutor(new SpawnHeadCommand());
		getCommand("reload").setExecutor(new ReloadCommand());
		getCommand("version").setExecutor(new VersionCommand());
	}

	public static HeadHunterPlugin getInstance()
	{
		return (HeadHunterPlugin) Bukkit.getPluginManager().getPlugin(PLUGIN_NAME);
	}

	public HeadConfig getHeadConfigByClass(Class clazz)
	{
		return headConfigs.get(clazz);
	}

	public HeadConfig getHeadConfigByPlayerName(String playerName)
	{
		return headConfigs.get(headNames.get(playerName));
	}

	public boolean doesHeadConfigsContainPlayerName(String playerName)
	{
		return headNames.containsKey(playerName);
	}

	public boolean isWorldDisabled(String worldName)
	{
		return disabledWorlds.contains(worldName);
	}

	public boolean isLootingEnabled()
	{
		return isLootingEnabled;
	}

	public boolean dropOnPlayerKillOnly()
	{
		return dropOnPlayerKillOnly;
	}

	public void reload()
	{
		headConfigs.clear();
		headNames.clear();
		disabledWorlds.clear();
		loadConfiguration();
	}
}
