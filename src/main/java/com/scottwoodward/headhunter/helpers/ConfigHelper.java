package com.scottwoodward.headhunter.helpers;

import java.util.List;

import com.scottwoodward.headhunter.HeadHunter;

public class ConfigHelper {
    public static HeadHunter plugin;
    
    public ConfigHelper(HeadHunter plugin){
        ConfigHelper.plugin = plugin;
    }
    
    public static boolean getBoolean(String key){
        return plugin.getConfig().getBoolean("UseDrops."+key);
    }
    
    public static double getDropChance(String key){
        return plugin.getConfig().getDouble("DropChance."+key);
    }
    public static boolean getLooting() {
    	return plugin.getConfig().getBoolean("ApplyLooting");
    }
    /**
     * Gets worlds from Config file.
     * 
     * @return A list of worlds that are present under DisabledWorlds section of config file.
     */
    public static List<String> getWorlds() {
    	return plugin.getConfig().getStringList("DisabledWorlds");
    }
}
