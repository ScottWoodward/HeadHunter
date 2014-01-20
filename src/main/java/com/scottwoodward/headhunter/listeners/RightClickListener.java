package com.scottwoodward.headhunter.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickListener implements Listener{

	@EventHandler
	public void onClick(PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().hasPermission("headhunter.flag.identify")){
			Block block = event.getClickedBlock();
			if(block.getType() == Material.SKULL){
				Skull skull = (Skull)block.getState();
				String message = null;
				if(skull.hasOwner()){
					String name = skull.getOwner();
					name = name.replace("MHF_", ChatColor.YELLOW + "a " + ChatColor.GREEN);
					if(name.charAt(name.length() - 1) == 's'){
						message = ChatColor.YELLOW + "That is " + ChatColor.GREEN + name + ChatColor.YELLOW + "' head!";
					}else{
						message = ChatColor.YELLOW + "That is " + ChatColor.GREEN + name + ChatColor.YELLOW + "'s head!";
					}
					event.getPlayer().sendMessage(message);
				}else if(skull.getSkullType() == SkullType.CREEPER){
					message = ChatColor.YELLOW + "That is a " + ChatColor.GREEN + "Creeper" + ChatColor.YELLOW + "'s head!";
				}else if(skull.getSkullType() == SkullType.SKELETON){
					message = ChatColor.YELLOW + "That is a " + ChatColor.GREEN + "Skeleton" + ChatColor.YELLOW + "'s skull!";
				}else if(skull.getSkullType() == SkullType.WITHER){
					message = ChatColor.YELLOW + "That is a " + ChatColor.GREEN + "Wither Skeleton" + ChatColor.YELLOW + "'s skull!";
				}else if(skull.getSkullType() == SkullType.ZOMBIE){
					message = ChatColor.YELLOW + "That is a " + ChatColor.GREEN + "Zombie" + ChatColor.YELLOW + "'s head!";
				}
			}
		}
	}
}
