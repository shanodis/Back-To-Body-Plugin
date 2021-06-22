package me.shanodis.BackToBodyPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ErrorHandler {
	
	private Player player;
	
	public ErrorHandler(Player player) {
		this.player = player;
	}
	
	public void handle(int type) {
		String standardMessage = ChatColor.RED + "(BackToBodyPlugin): " + ChatColor.ITALIC.toString() + "Failed to execute the command! ";
		
		switch (type) {
		case 1:
			player.sendMessage(standardMessage + "Your nickname cannot be found in a death list!");
			break;
			
		case 2:
			player.sendMessage(standardMessage + "You can't give arguments to this command!");
			break;

		default:
			break;
		}
	}
}
