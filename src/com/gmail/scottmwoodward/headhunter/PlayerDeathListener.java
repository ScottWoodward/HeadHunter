/**
 * PlayerDeathListener.java
 * Purpose: Implements the listener for reacting to player deaths, caused by another player
 * 
 * @version 1.0.0 10/31/12
 * @author Scott Woodward
 */
package com.gmail.scottmwoodward.headhunter;

import net.minecraft.server.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener{

    /**
     * Sentinel value signifying a full inventory
     */
    public static final int FULL_INVENTORY = -1;

    /**
     * This is the action executed when a player dies.
     * If the player was killed by another player, the
     * victim will drop a copy of his head.
     * 
     * @param event is the triggering event, used to get details on killed player
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player){
            if(event.getEntity().getKiller().getInventory().firstEmpty()!= FULL_INVENTORY){
                CraftItemStack item = new CraftItemStack(Material.SKULL_ITEM, 1, (short)3);
                NBTTagCompound name = new NBTTagCompound();
                name.setString("SkullOwner", event.getEntity().getName());
                Bukkit.getLogger().info(name.getString("SkullOwner"));
                item.getHandle().tag = name;
                Item drop = event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(),  item);
                drop.setItemStack(item);
            }
        }
    }
}
