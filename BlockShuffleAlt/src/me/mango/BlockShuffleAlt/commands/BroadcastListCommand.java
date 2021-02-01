package me.mango.BlockShuffleAlt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mango.BlockShuffleAlt.Main;

public class BroadcastListCommand implements CommandExecutor {

	private Main plugin;
	
	public BroadcastListCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("blockshufflelist").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p.getServer().broadcastMessage("The list of blocks you were supposed to find is:");
		for(int i = 0; i<this.plugin.blockamount; i++) {
			int currentblock = i+1;
			p.getServer().broadcastMessage(currentblock + ". " + this.plugin.findinglist.get(i).name());
		}
		return false;
	}
	
	

}
