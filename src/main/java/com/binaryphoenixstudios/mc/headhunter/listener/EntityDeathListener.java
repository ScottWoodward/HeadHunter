package com.binaryphoenixstudios.mc.headhunter.listener;

import com.binaryphoenixstudios.mc.headhunter.HeadHunterPlugin;
import com.binaryphoenixstudios.mc.headhunter.factory.HeadFactory;
import com.binaryphoenixstudios.mc.headhunter.model.HeadConfig;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by Scott on 2/21/2015.
 */
public class EntityDeathListener implements Listener
{
	protected SecureRandom random;

	public EntityDeathListener()
	{
		random = new SecureRandom();
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{
		HeadHunterPlugin plugin = HeadHunterPlugin.getInstance();
		HeadConfig headConfig = plugin.getHeadConfigByClass(event.getEntityType().getEntityClass());

		if(headConfig != null
				&& shouldHeadDrop(headConfig.getChance())
				&& (isPlayerKill(event) || !plugin.dropOnPlayerKillOnly())
				&& !plugin.isWorldDisabled(event.getEntity().getWorld().getName()))
		{
			Material material = Material.valueOf(headConfig.getMaterialName());
			int quantity = calculateDropQuantity(event, headConfig);
			String owner = null;
			String displayName = null;

			if(StringUtils.isNotEmpty(headConfig.getPlayerName()))
			{
				owner = headConfig.getPlayerName();
			}
			else if(event.getEntityType() == EntityType.PLAYER)
			{
				owner = event.getEntity().getName();
			}

			if(StringUtils.isNotEmpty(headConfig.getDisplayName()))
			{
				displayName=headConfig.getDisplayName();
			}

			ItemStack itemStack = new HeadFactory().getHead(material, headConfig.getHeadMetadata(), owner, displayName, quantity);

			event.getDrops().add(itemStack);
		}
	}

	protected boolean shouldHeadDrop(double dropChance)
	{
		double actual = random.nextDouble() * 100;
		return actual <= dropChance;
	}

	protected boolean isPlayerKill(EntityDeathEvent event)
	{
		return event.getEntity().getKiller() != null;
	}

	protected int calculateDropQuantity(EntityDeathEvent event, HeadConfig headConfig)
	{
		int drops = 1;
		HeadHunterPlugin plugin = HeadHunterPlugin.getInstance();
		if(plugin.isLootingEnabled())
		{
			Map<Enchantment, Integer> enchantments = event.getEntity().getKiller().getItemInHand().getEnchantments();
			if(enchantments != null && enchantments.containsKey(Enchantment.LOOT_BONUS_MOBS))
			{
				int level = enchantments.get(Enchantment.LOOT_BONUS_MOBS);
				for(int i = 0; i < level; i++)
				{
					if(shouldHeadDrop(headConfig.getChance()))
					{
						drops++;
					}
				}
			}
		}

		return drops;
	}
}
