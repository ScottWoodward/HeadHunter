/**
 * HeadHunter.java
 * Purpose: Registers all Listeners and Command Executors
 *          for the plugin.
 * 
 * @version 1.2.0 11/5/12
 * @author Scott Woodward
 */
package com.gmail.scottmwoodward.headhunter;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.scottmwoodward.headhunter.helpers.CommandHelper;
import com.gmail.scottmwoodward.headhunter.helpers.ConfigHelper;
import com.gmail.scottmwoodward.headhunter.listeners.EntityDeathListener;
import com.gmail.scottmwoodward.headhunter.listeners.HeadWaterBreakListener;

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
        getCommand("spawnhead").setExecutor(new CommandHelper());
        new ConfigHelper(this);
    }
}
