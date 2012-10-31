/**
 * HeadHunter.java
 * Purpose: Registers all Listeners and Command Executors
 *          for the plugin.
 * 
 * @version 1.0.0 10/31/12
 * @author Scott Woodward
 */
package com.gmail.scottmwoodward.headhunter;

import org.bukkit.plugin.java.JavaPlugin;

public class HeadHunter extends JavaPlugin{

    /**
     * Called when the plugin is enabled upon server
     * startup. Registers all events and commands for
     * the plugin
     */
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }
}
