package me.didi.listeners.ingame;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class IngameListener implements Listener {

	private JoinListener joinListener;
	
	public IngameListener() {
		this.joinListener = new JoinListener();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		joinListener.onJoin(event);
	}
	
}
