package me.didi.gamephases;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.didi.listeners.ingame.IngameListener;
import me.didi.listeners.postgame.PostGameListener;
import me.didi.listeners.pregame.PreGameListener;

public class GamePhaseSwitcher {

	public static final int PRE = 0, INGAME = 1, POST = 2;
	private GamePhase[] phases;
	private GamePhase currentPhase;
	private Plugin plugin;

	/**
	 * Initialises the GamePhase switcher with the "PRE" gamePhase
	 * */
	public GamePhaseSwitcher(Plugin plugin) {
		this.plugin = plugin;
		phases = new GamePhase[3];
		phases[0] = new PreGamePhase(new PreGameListener());
		phases[1] = new IngamePhase(new IngameListener());
		phases[2] = new PostGamePhase(new PostGameListener());
		
		setCurrentGamePhase(GamePhaseSwitcher.PRE);
	}

	
	/**
	 * Stops the current game phase, then starts the next one
	 * */
	public void setCurrentGamePhase(int phaseIndex) {
		currentPhase.stopPhase();
		currentPhase = phases[phaseIndex];
		Bukkit.getPluginManager().registerEvents(currentPhase.getPhaseListener(), plugin);
		currentPhase.initPhase();
	}
	
}
