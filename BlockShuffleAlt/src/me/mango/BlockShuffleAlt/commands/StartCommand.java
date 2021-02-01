package me.mango.BlockShuffleAlt.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mango.BlockShuffleAlt.Main;

public class StartCommand implements CommandExecutor {
	
	private Main plugin;
	
	public StartCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("blockshufflestart").setExecutor(this);
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
//		Player p = (Player) sender;
//		BukkitScheduler sched = p.getServer().getScheduler();
//		Material[] mats = Material.values();
//		Random rand = new Random();
//		for (int i = 0; i <args.length; i++) {
//			this.plugin.plmap.put(args[i], null);
//		}
//		
//		p.getServer().broadcastMessage("Starting Block Shuffle Game");
//		
//		sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
//
//			int task1;
//			
//			@Override
//			public void run() {
//				task1 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {					
//					int cnt = 20;
//					boolean end = false;
//					Material mat;
//					
//					@Override
//					public void run() {
//						if (cnt == 0) {
//							
//							for (Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
//								if(entry.getValue() != null) {
//									p.getServer().broadcastMessage("The Game is Over!" + entry.getKey() +" could not find their block!");
//									p.getServer().getScheduler().cancelTasks(plugin);
//									end = true;
//								}
//								if(!end) {
//									mat = mats[rand.nextInt(mats.length - 1)];
//									while(!mat.isSolid() || 
//											mat.name().contains("SHULKER") || 
//											mat.name().contains("BANNER") || 
//											mat.name().contains("DOOR") || 
//											mat.name().contains("COMMAND") || 
//											mat.name().contains("DEAD") || 
//											mat.name().contains("DIAMOND_BLOCK") || 
//											mat.name().contains("EMERALD") || 
//											mat.name().contains("END") || 
//											mat.name().contains("GOLD_BLOCK") || 
//											mat.name().contains("LAPIS_BLOCK") || 
//											mat.name().contains("HEAD")) {
//										mat = mats[rand.nextInt(mats.length - 1)];
//									}
//								}
//								plugin.plmap.replace(entry.getKey(), mat);
//								p.getServer().getPlayer(entry.getKey()).sendMessage("You have 5 minutes to find: " + mat.name());
//				
//							}
//							
//							sched.cancelTask(task1);
//							
//						}else {
//							p.getServer().broadcastMessage(Integer.toString(cnt--));
//							
//						}
//						
//					}
//					
//				}, 0L, 20L);
//				
//			}
//			
//		}, 0L, 6000L);
//		// Previous one relies on timer - counter, boolean end, and the mat
//		//    nothing to detect if all players are done before the timer is up
//		//    uses the scheduleSyncRepeatingTask for the timer
//		//
//		//    
//		// so we now use a point system -
//		//    the hashmap is gonna be different though right since we're using points instead of the actual object
//		//    do we need a new class for this? to just breakdown the player themselves?
//		//      I think we can stick with HashMap and give everyone the same order of blocks (or else rng will just screw ppl over)
//		//    also how do we implement that scoreboard thing?
//		//    and we need to make messages that broadcast when other people found their block
//		//    let's just not use the counter I don't like it
//		//    we'll still need the mat
//		//    crap we need to revise the lists of items so we don't get like freaking lodestone or smth
//		//    OK WHILE LOOP IS WHILE NO PLAYERS HAVE REACHED THE TOTAL SCORE AND "END" IS FALSE (FLIP END TO TRUE IF WE WANNA STOP THE GAME I'M A GENIUS)
//		//    ok wait the smart ppl online are saying we should have the total score detector as a boolean and have a separate foreach loop that looks in
		//    the list of values in our Hashmap and return a boolean for us
		//    ok wait if we're just "listening" for the point when someone gets all the blocks... should everything be in listeners?
		
		Player p = (Player) sender;
		
		if (Double.isNaN(Integer.parseInt(args[0]))) {
			p.getServer().broadcastMessage(args[0] + " is not a number! (ex. Type in '10' as your first argument if you'd like to start the game with 10 blocks to find");
		} else {
			this.plugin.blockamount = Integer.parseInt(args[0]);
			
			this.plugin.findinglist = constructfindinglist(this.plugin.blockamount);
			
			
			for (int i = 1; i <args.length; i++) {
				this.plugin.plmap.put(args[i], 0);
			}
			
			p.getServer().broadcastMessage(ChatColor.GOLD + "Starting Block Shuffle Game");
			
			Main.updatescoreboard(p, this.plugin.plmap);
			
			p.getServer().broadcastMessage(
					ChatColor.AQUA + "The first block for everyone to find is " + 
					ChatColor.AQUA + this.plugin.findinglist.get(0) + "!");
			
//			int stubcounter = 0;
//			
//			while(this.plugin.haswinner == false && this.plugin.manualstop == false) {
//				if(stubcounter>10) {
//					stubcounter=0;
//				} else {
//					stubcounter++;
//				}
//			}
			
//			if(this.plugin.manualstop == true) {
//				p.getServer().broadcastMessage("Manually Stopping Block Shuffle...");
//			} else if(this.plugin.haswinner == true) {
//				String winnername = "playerstub"; //!!!
//				for (Map.Entry<String, Integer> entry : this.plugin.plmap.entrySet()) {
//					if(entry.getValue() >= this.plugin.blockamount) {
//						winnername = entry.getKey();
//					}
//				}
//				p.getServer().broadcastMessage("The Game is over! " + winnername +" has found all the blocks!");
//			} else {
//				p.getServer().broadcastMessage("The Game is over! Michael get better at coding because this message shouldn't be popping up");
//			}
//			
		}
		
		return true;
			
	}
	
	private ArrayList<Material> constructfindinglist(int numbertofind) {
		Material[] mats = Material.values();
  		Random rand = new Random();
  		ArrayList<Material> thelist = new ArrayList<Material>();
  		
  		for(int i = 0; i < numbertofind; i++) {
  			Material mat = mats[rand.nextInt(mats.length - 1)];
  			while(!mat.isSolid() || 
  					mat.name().contains("BANNER") || 
					mat.name().contains("DOOR") || 
					mat.name().contains("COMMAND") ||
  					mat.name().contains("CORAL") || 
					mat.name().contains("HEAD") ||  
					mat.name().contains("SKULL") ||  
					mat.name().contains("BEACON") ||  
					mat.name().contains("SPONGE") ||  
					mat.name().contains("SPAWNER") ||
					mat.name().contains("INFESTED") ||
					mat.name().contains("MYCELIUM") ||
					mat.name().contains("ENCHANTMENT") ||
					mat.name().contains("PRISMARINE") ||
					mat.name().contains("PACKED") ||
					mat.name().contains("BLUE_ICE") ||
					mat.name().contains("FROSTED") ||
					mat.name().contains("BEEHIVE") ||
					mat.name().contains("HONEY") ||
					mat.name().contains("GILDED") ||
					mat.name().contains("BARRIER") ||
					mat.name().contains("VOID") ||
					mat.name().contains("STRUCTURE") ||
					
					mat.name().contains("DIAMOND_BLOCK") ||
					mat.name().contains("GOLD_BLOCK") ||
					mat.name().contains("EMERALD") ||
					mat.name().contains("NETHERITE") ||
					mat.name().contains("ANCIENT") ||
					mat.name().contains("LODESTONE") ||
					mat.name().contains("ANCHOR") ||
  					
					mat.name().contains("END") ||
					mat.name().contains("SHULKER") ||
					mat.name().contains("PURPUR") ||
					mat.name().contains("CHORUS") ||
  					mat.name().contains("DRAGON")) {
				mat = mats[rand.nextInt(mats.length - 1)];
			}
  			thelist.add(mat);
  		}
  		
  		return thelist;
	}

	
}