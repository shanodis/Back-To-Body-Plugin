package me.shanodis.BackToBodyPlugin;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PluginListener implements Listener {
	
	private HashMap<String, Location> playerList;
	
	public PluginListener(HashMap<String, Location> playerList) {
		this.playerList = playerList;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Location cordinates = player.getLocation();
		Location location = new Location(player.getWorld(), cordinates.getBlockX(), cordinates.getBlockY(), cordinates.getZ());
		
		playerList.put(player.getName(), location);
		player.sendMessage("(BackToBodyPlugin): You have died! Return to your body by typing the command: /back");
	}
}
