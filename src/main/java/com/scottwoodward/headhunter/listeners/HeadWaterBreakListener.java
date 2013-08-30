/**
 * HeadWaterBreakListener.java
 * Purpose: Implements the listener for reacting to head breaks, providing
 *          a work around for a current Bukkit bug
 * 
 * @version 1.2.1 11/13/12
 * @author Scott Woodward
 */
package com.scottwoodward.headhunter.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class HeadWaterBreakListener implements Listener{
    
    @EventHandler
    public void onBlockBreak(BlockFromToEvent event){   
        if(event.getToBlock().getType().equals(Material.SKULL)){
            event.setCancelled(true);
        }
    }

}