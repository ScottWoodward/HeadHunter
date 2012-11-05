package com.gmail.scottmwoodward.headhunter.helpers;

import com.gmail.scottmwoodward.headhunter.HeadHunter;

public class ConfigHelper {
    public static HeadHunter plugin;
    
    public ConfigHelper(HeadHunter plugin){
        ConfigHelper.plugin = plugin;
    }
    
    public static boolean getBoolean(String key){
        return plugin.getConfig().getBoolean("UseDrops."+key);
    }
    
    public static double getDouble(String key){
        return plugin.getConfig().getDouble("DropChance."+key);
    }

}
