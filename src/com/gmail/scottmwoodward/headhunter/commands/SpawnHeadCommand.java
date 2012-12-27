package com.gmail.scottmwoodward.headhunter.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SpawnHeadCommand {

	public static void spawnHead(Player player, String name) {
        int firstEmpty = player.getInventory().firstEmpty();
        if(firstEmpty == -1){
            player.sendMessage(ChatColor.YELLOW+"You have no room in your inventory");
        }
        else{
            player.getInventory().addItem(setSkin(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), name));
        }
    }
    private static ItemStack setSkin(ItemStack item, String nick) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(nick);
        item.setItemMeta(meta);
        return item;
    }
}
