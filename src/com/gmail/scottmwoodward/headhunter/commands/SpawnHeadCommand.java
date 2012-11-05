package com.gmail.scottmwoodward.headhunter.commands;

import net.minecraft.server.NBTTagCompound;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class SpawnHeadCommand {

    public static void spawnHead(Player player, String name) {
        int firstEmpty = player.getInventory().firstEmpty();
        if(firstEmpty == -1){
            player.sendMessage(ChatColor.YELLOW+"You have no room in your inventory");
        }
        else{
            CraftItemStack item = new CraftItemStack(Material.SKULL_ITEM, 1, (short)3);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("SkullOwner", name);
            item.getHandle().tag = tag;
            player.getInventory().setItem(firstEmpty, item);
        }
    }

}
