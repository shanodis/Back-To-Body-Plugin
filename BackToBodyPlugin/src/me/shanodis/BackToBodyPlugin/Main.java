package me.shanodis.BackToBodyPlugin;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private HashMap<String, Location> playerList;
	
	public Main() {
		playerList = new HashMap<>();
	}
	
	@Override
	public void onEnable() {
		PluginManager pluginManager = getServer().getPluginManager();
		PluginListener pluginListener = new PluginListener(playerList);
		
		pluginManager.registerEvents(pluginListener, this);
		getLogger().info("BackToBody Plugin has been loaded!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("BackToBody Plugin has been removed!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if (!(sender instanceof Player))
			return false;
		
		String lowerCmd = cmd.getName().toLowerCase();
		String playerNickname = player.getName();
		ErrorHandler errorHandler = new ErrorHandler(player);
		
		if (!playerList.containsKey(playerNickname)) {
			errorHandler.handle(1);
			return false;
		}
		
		if (args.length != 0) {
			errorHandler.handle(2);
			return false;
		}
		
		if (lowerCmd.equals("back")) {
			Location location = playerList.get(playerNickname);
			
			new DangerChecker().run(location);
			player.teleport(location);
			playerList.remove(playerNickname);
			
			player.sendMessage(
					ChatColor.BLUE + "(BackToBodyPlugin): " + 
					ChatColor.ITALIC.toString() + "You have been teleported to your body!"
			);
		}
		
		return true;
	}
}
