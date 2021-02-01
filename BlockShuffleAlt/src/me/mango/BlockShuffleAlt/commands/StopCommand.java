package me.mango.BlockShuffleAlt.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.mango.BlockShuffleAlt.Main;

public class StopCommand implements CommandExecutor {

	private Main plugin;
	
	public StopCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("blockshufflestop").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p.getServer().broadcastMessage(ChatColor.GOLD + "Manually Stopping Block Shuffle...");
		ArrayList<String> playernameslist = new ArrayList<String>(this.plugin.plmap.keySet());
		for (int i = 0; i <playernameslist.size(); i++) {
			this.plugin.plmap.replace(playernameslist.get(i), this.plugin.blockamount + 1);
		}
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = manager.getNewScoreboard();
		
		Objective objective = scoreboard.registerNewObjective("Block Shuffle", "dummy", ChatColor.BOLD + "BLOCK SHUFFLE");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		ArrayList<Player> playerlist = new ArrayList<>(Bukkit.getOnlinePlayers());
		for (int i = 0; i < playerlist.size(); i++) {
			playerlist.get(i).setScoreboard(scoreboard);
		}
		
		return false;
	}
	
	

}
