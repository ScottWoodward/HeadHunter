package com.binaryphoenixstudios.mc.headhunter.listener;

import com.binaryphoenixstudios.mc.headhunter.HeadHunterPlugin;
import com.binaryphoenixstudios.mc.headhunter.factory.HeadFactory;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Scott on 2/21/2015.
 */
public class BlockBreakListener implements Listener
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.getBlock().getType() == Material.SKULL)
		{
			Block block = event.getBlock();
			Skull skull = (Skull)block.getState();
			if(skull.hasOwner())
			{
				block.setType(Material.AIR);

				String owner = skull.getOwner();
				HeadHunterPlugin plugin = HeadHunterPlugin.getInstance();
				String displayName = plugin.getHeadConfigByPlayerName(skull.getOwner()).getDisplayName();

				ItemStack itemStack = new HeadFactory().getHead(Material.SKULL_ITEM, (short)3, owner, displayName, 1);

				World world = block.getWorld();
				world.dropItemNaturally(block.getLocation(), itemStack);
				event.setCancelled(true);
			}

		}
	}
}
