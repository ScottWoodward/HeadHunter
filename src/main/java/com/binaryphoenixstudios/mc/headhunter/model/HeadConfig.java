package com.binaryphoenixstudios.mc.headhunter.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Scott on 2/21/2015.
 */
public class HeadConfig
{
	public static final String PROPERTY_KEY_ENTITY_CLASS = "entityClass";
	public static final String PROPERTY_KEY_MATERIAL_NAME = "materialName";
	public static final String PROPERTY_KEY_HEAD_METADATA = "headMetaData";
	public static final String PROPERTY_KEY_PLAYER_NAME = "playerName";
	public static final String PROPERTY_KEY_DISPLAY_NAME = "displayName";
	public static final String PROPERTY_KEY_CHANCE = "chance";

	private String entityClass;
	private String materialName;
	private short headMetadata;
	private String playerName;
	private double chance;
	private String displayName;

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("entityClass", entityClass);
		builder.append("materialName", materialName);
		builder.append("headMetadata", headMetadata);
		builder.append("playerName", playerName);
		builder.append("chance", chance);
		return builder.toString();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Getters & Setters
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getEntityClass()
	{
		return entityClass;
	}

	public void setEntityClass(String entityClass)
	{
		this.entityClass = entityClass;
	}

	public String getMaterialName()
	{
		return materialName;
	}

	public void setMaterialName(String materialName)
	{
		this.materialName = materialName;
	}

	public short getHeadMetadata()
	{
		return headMetadata;
	}

	public void setHeadMetadata(short headMetadata)
	{
		this.headMetadata = headMetadata;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public double getChance()
	{
		return chance;
	}

	public void setChance(double chance)
	{
		this.chance = chance;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
}
