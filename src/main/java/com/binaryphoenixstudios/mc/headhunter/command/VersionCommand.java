package com.binaryphoenixstudios.mc.headhunter.command;

import com.binaryphoenixstudios.mc.headhunter.HeadHunterPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Created by Scott on 2/21/2015.
 */
public class VersionCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
	{
		PluginDescriptionFile description =  HeadHunterPlugin.getInstance().getDescription();
		StringBuilder versionString = new StringBuilder();
		versionString.append(description.getName()).append(" version: ").append(description.getVersion());
		commandSender.sendMessage(versionString.toString());
		return true;
	}
}
