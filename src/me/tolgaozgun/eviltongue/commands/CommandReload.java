package me.tolgaozgun.eviltongue.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.eviltongue.TongueMain;
import me.tolgaozgun.eviltongue.util.Settings;
import net.md_5.bungee.api.ChatColor;

public class CommandReload {
	private static TongueMain plugin = TongueMain.getPlugin(TongueMain.class);
	
	public static void main(Player p) {
		plugin.onDisable();
		plugin.onEnable();
		p.sendMessage(Settings.PLTAG + ChatColor.GREEN + "Reloaded successfully.");
	}
	
	public static void main(CommandSender sender) {
		plugin.onDisable();
		plugin.onEnable();
		sender.sendMessage(Settings.PLTAG + ChatColor.GREEN + "Reloaded successfully.");
		
	}

}
