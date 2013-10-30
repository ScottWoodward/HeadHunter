/**
 * HeadHunter.java
 * Purpose: Registers all Listeners and Command Executors
 *          for the plugin.
 * 
 * @version 1.2.0 11/5/12
 * @author Scott Woodward
 */
package com.scottwoodward.headhunter;

import org.bukkit.plugin.java.JavaPlugin;

import com.scottwoodward.headhunter.helpers.CommandHelper;
import com.scottwoodward.headhunter.helpers.ConfigHelper;
import com.scottwoodward.headhunter.listeners.EntityDeathListener;
import com.scottwoodward.headhunter.listeners.HeadWaterBreakListener;
import com.scottwoodward.headhunter.listeners.RightClickListener;

public class HeadHunter extends JavaPlugin{

    /**
     * Called when the plugin is enabled upon server
     * startup. Registers all events and commands for
     * the plugin
     */
    public void onEnable(){
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new HeadWaterBreakListener(),this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getCommand("spawnhead").setExecutor(new CommandHelper());
        new ConfigHelper(this);
    }
}
