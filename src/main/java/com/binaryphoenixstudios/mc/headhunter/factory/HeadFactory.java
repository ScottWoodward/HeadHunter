package com.binaryphoenixstudios.mc.headhunter.factory;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by Scott on 2/21/2015.
 */
public class HeadFactory
{
	public ItemStack getHead(Material material, short metadata, String playerName, String displayName, int quantity)
	{
		ItemStack head = new ItemStack(material, quantity, metadata);
		if(metadata == 3)
		{
			SkullMeta meta = (SkullMeta)head.getItemMeta();
			meta.setOwner(playerName);

			if(StringUtils.isNotEmpty(displayName))
			{
				meta.setDisplayName(displayName);
			}
			head.setItemMeta(meta);
		}
		return head;
	}
}
