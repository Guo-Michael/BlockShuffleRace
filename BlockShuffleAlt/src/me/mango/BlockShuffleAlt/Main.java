package me.mango.BlockShuffleAlt;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.mango.BlockShuffleAlt.commands.BroadcastListCommand;
import me.mango.BlockShuffleAlt.commands.CommandListener;
import me.mango.BlockShuffleAlt.commands.StartCommand;
import me.mango.BlockShuffleAlt.commands.StopCommand;

public class Main extends JavaPlugin {
	
	public HashMap<String,Integer> plmap = new HashMap<String, Integer>();
	public int blockamount = 0;
	public ArrayList<Material> findinglist = new ArrayList<Material>();
	
	@Override
	public void onEnable() {
		new StartCommand(this);
		new StopCommand(this);
		new CommandListener(this);
		new BroadcastListCommand(this);
	}
	
	public static void updatescoreboard(Player p, HashMap<String,Integer> plmap) {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = manager.getNewScoreboard();
		
		Objective objective = scoreboard.registerNewObjective("Block Shuffle", "dummy", ChatColor.BOLD + "BLOCK SHUFFLE");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		ArrayList<String> playernameslist = new ArrayList<String>(plmap.keySet());
		for (int i = 0; i < playernameslist.size(); i++) {
			Score score = objective.getScore(ChatColor.GOLD + playernameslist.get(i));
			score.setScore(plmap.get(playernameslist.get(i)));
		}
		
		ArrayList<Player> playerlist = new ArrayList<>(Bukkit.getOnlinePlayers());
		for (int i = 0; i < playerlist.size(); i++) {
			playerlist.get(i).setScoreboard(scoreboard);
		}
		
		p.setScoreboard(scoreboard);
		
	}
	


}