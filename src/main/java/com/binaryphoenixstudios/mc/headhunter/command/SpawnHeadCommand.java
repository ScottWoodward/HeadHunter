package com.binaryphoenixstudios.mc.headhunter.command;

import com.binaryphoenixstudios.mc.headhunter.factory.HeadFactory;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;

/**
 * Created by Scott on 2/21/2015.
 */
public class SpawnHeadCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)
	{
		boolean success = true;
		if(!(commandSender instanceof Player))
		{
			commandSender.sendMessage(ChatColor.DARK_RED + "Only players may spawn heads");
		}
		if(ArrayUtils.isEmpty(args))
		{
			spawnHead((Player)commandSender, null);
		}
		else if(args.length == 1)
		{
			spawnHead((Player)commandSender, args[0]);
		}
		else
		{
			success = false;
		}
		return success;
	}

	protected void spawnHead(Player player, String playerName)
	{
		Inventory inventory = player.getInventory();
		if(inventory.firstEmpty() < 1)
		{
			player.sendMessage(ChatColor.DARK_RED + "You do not have enough inventory space");
		}
		else
		{
			String owner = StringUtils.isEmpty(playerName) ? player.getName() : playerName;
			ItemStack itemStack = new HeadFactory().getHead(Material.SKULL_ITEM, (short)3, owner, null, 1);
			inventory.addItem(itemStack);
		}
	}
}
