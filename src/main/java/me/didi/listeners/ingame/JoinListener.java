package me.didi.listeners.ingame;

import org.bukkit.event.player.PlayerJoinEvent;

import me.didi.utils.TaskManager;

public class JoinListener {

	public void onJoin(PlayerJoinEvent event) {
		TaskManager.getInstance().runTaskLater(1, t -> {
			event.getPlayer().kickPlayer("The round has already begun.");
		});
	}

}
