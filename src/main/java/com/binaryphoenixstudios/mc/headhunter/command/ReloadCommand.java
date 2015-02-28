package com.binaryphoenixstudios.mc.headhunter.command;

import com.binaryphoenixstudios.mc.headhunter.HeadHunterPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Scott on 2/21/2015.
 */
public class ReloadCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
	{
		HeadHunterPlugin.getInstance().reload();
		return true;
	}
}
