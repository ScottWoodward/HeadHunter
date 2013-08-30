package com.scottwoodward.headhunter.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.scottwoodward.headhunter.commands.SpawnHeadCommand;


public class CommandHelper implements CommandExecutor{
    public CommandHelper(){
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to spawn a head");
        }else if(sender.hasPermission("headhunter.command.spawnhead")){
            if(args.length == 1){
                SpawnHeadCommand.spawnHead((Player)sender, args[0]);
            }
            else{
                sender.sendMessage(ChatColor.YELLOW+"Usage: /spawnhead <playername>");
            }
        }
        return true;
    }

}
