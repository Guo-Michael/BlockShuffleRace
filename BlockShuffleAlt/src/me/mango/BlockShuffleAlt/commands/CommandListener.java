package me.mango.BlockShuffleAlt.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import me.mango.BlockShuffleAlt.Main;

public class CommandListener implements Listener {
	
	private Main plugin;
	
	public CommandListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int currentscore = this.plugin.plmap.get(p.getName());
		Material bmat = e.getTo().clone().subtract(0,1,0).getBlock().getType();
		
		if(currentscore >= this.plugin.blockamount) {
			
		} else {
			Material pmat = this.plugin.findinglist.get(currentscore);
			
			if(bmat.equals(pmat)) {
				
				currentscore++;
				p.getServer().broadcastMessage(
						ChatColor.GREEN + p.getName() + 
						ChatColor.GREEN + " has found block number "+ 
						ChatColor.GREEN + currentscore + 
						ChatColor.GREEN + "!");
				
				if(currentscore == this.plugin.blockamount) {
					this.plugin.plmap.replace(p.getName(), currentscore);
					Main.updatescoreboard(p, this.plugin.plmap);
					p.getServer().broadcastMessage(
							ChatColor.GREEN + "The Game is over! " + 
							ChatColor.GREEN + p.getName() + 
							ChatColor.GREEN + " has found all the blocks!");
					ArrayList<String> playerlist = new ArrayList<String>(this.plugin.plmap.keySet());
					for (int i = 0; i <playerlist.size(); i++) {
						this.plugin.plmap.replace(playerlist.get(i), this.plugin.blockamount + 1);
					}
				} else {
					this.plugin.plmap.replace(p.getName(), currentscore);
					Material nextmat = this.plugin.findinglist.get(currentscore);
					p.getServer().getPlayer(p.getName()).sendMessage(
							ChatColor.AQUA + "Your next block to find is " + 
							ChatColor.AQUA + nextmat.name());
					Main.updatescoreboard(p, this.plugin.plmap);
				}
				
			}
		}
		
	}
	
	

}