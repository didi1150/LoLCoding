package me.didi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.didi.gamephases.GamePhaseSwitcher;
import me.didi.utils.TaskManager;

public class LoLMain extends JavaPlugin{

	private GamePhaseSwitcher gamePhaseSwitcher;
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("LoL initialised");
		TaskManager.init(this);
		gamePhaseSwitcher = new GamePhaseSwitcher(this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
}
