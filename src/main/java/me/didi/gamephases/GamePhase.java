package me.didi.gamephases;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class GamePhase {

	protected Listener phaseListener;
	
	public Listener getPhaseListener() {
		return phaseListener;
	}
	
	public GamePhase(Listener phaseListener) {
		this.phaseListener = phaseListener;
	}

	public abstract void initPhase();
	
	public void stopPhase() {
		HandlerList.unregisterAll(phaseListener);
	}
	
}
