package com.binaryphoenixstudios.mc.headhunter.listener;

import com.binaryphoenixstudios.mc.headhunter.HeadHunterPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Scott on 2/21/2015.
 */
public class PlayerInteractListener implements Listener
{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.getClickedBlock().getType() == Material.SKULL)
			{
				Block block = event.getClickedBlock();
				Skull skull = (Skull)block.getState();
				if(skull.hasOwner())
				{
					String displayName = skull.getOwner();
					HeadHunterPlugin plugin = HeadHunterPlugin.getInstance();
					if(plugin.doesHeadConfigsContainPlayerName(skull.getOwner()))
					{
						displayName = "a " + plugin.getHeadConfigByPlayerName(skull.getOwner()).getDisplayName();
					}
					event.getPlayer().sendMessage(ChatColor.GREEN + "That's " + displayName);
				}

			}
		}
	}
}
