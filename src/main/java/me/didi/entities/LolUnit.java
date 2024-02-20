package me.didi.entities;

import org.bukkit.Location;

public abstract class LolUnit {

	private String name;
	private int attackDamage;
	private double attackSpeed;
	private int armor;
	private int magicResistance;
	private int movementSpeed;
	private int unitRadius;
	
	private Location currentPosition;
	private Location respawnPosition;
}
