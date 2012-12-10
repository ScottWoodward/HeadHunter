/**
 * MobDeathListener.java
 * Purpose: Implements the listener for reacting to mob deaths, caused by a player
 * 
 * @version 1.2.0 11/5/12
 * @author Scott Woodward
 */
package com.gmail.scottmwoodward.headhunter.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_4_5.entity.CraftSkeleton;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.gmail.scottmwoodward.headhunter.helpers.DropHelper;
import com.gmail.scottmwoodward.headhunter.helpers.HeadType;

public class EntityDeathListener implements Listener{

    /**
     * This is the action executed when an entity dies.
     * If the player was killed by another player, the
     * victim will drop a copy of his head.
     * 
     * @param event is the triggering event, used to get details on killed player
     */
    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player){
            World world = event.getEntity().getWorld();
            Location loc = event.getEntity().getLocation();
            if(event.getEntity() instanceof Player){
                DropHelper.drop(HeadType.HUMAN, loc, ((Player)event.getEntity()).getName(), world);
            }else if(event.getEntity() instanceof Zombie){
                DropHelper.drop(HeadType.ZOMBIE, loc, null, world);
            }else if(event.getEntity() instanceof Creeper){
                DropHelper.drop(HeadType.CREEPER, loc, null, world);
            }else if(event.getEntity() instanceof Skeleton){
                CraftSkeleton skeleton = (CraftSkeleton)event.getEntity();
                if(skeleton.getHandle().getSkeletonType() == 1){
                    DropHelper.drop(HeadType.WITHERSKELETON, loc, null, world);
                }else{
                    DropHelper.drop(HeadType.SKELETON, loc, null, world);
                }
            }
        }
    }

}
