package com.gbates31.imagemaker;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean successful = false;
		
		if (args.length > 0) {
			int dimension = Integer.valueOf(args[0]) * 2;
			World world = Bukkit.getServer().getWorlds().get(0);

			ImageMaker im = new ImageMaker(world, dimension);
			im.createImage();
			successful = true;
		}
		
		return successful;
	}
	
	public static void c(String text) {
		Bukkit.getLogger().info(text);
	}
}