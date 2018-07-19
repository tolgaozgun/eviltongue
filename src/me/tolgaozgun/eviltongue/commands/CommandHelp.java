package me.tolgaozgun.eviltongue.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.eviltongue.TongueMain;
import me.tolgaozgun.eviltongue.util.Settings;
import net.md_5.bungee.api.ChatColor;

public class CommandHelp {
	TongueMain plugin = TongueMain.getPlugin(TongueMain.class);
	
	public static void main(Player p) {

		p.sendMessage(Settings.PLTAG + ChatColor.YELLOW + "---- Available Commands ----");
		p.sendMessage(Settings.PLTAG + ChatColor.RED + "/et reload" + ChatColor.RESET + " - Reloads the configuration files.");
		p.sendMessage(Settings.PLTAG + ChatColor.RED + "/et help" + ChatColor.RESET + " - Displays this page.");
		p.sendMessage(Settings.PLTAG + ChatColor.RED + "/et update" + ChatColor.RESET + " - (Console command) Updates the plugin.");
		p.sendMessage(Settings.PLTAG + ChatColor.BLUE + "Evil Tongue v1.0 plugin by Progr4mm3r");
		p.sendMessage(Settings.PLTAG + ChatColor.BLUE + "http://www.tolgaozgun.me");
		 
	}
	
	public static void main(CommandSender sender) {

		sender.sendMessage(Settings.PLTAG + ChatColor.YELLOW + "---- Available Commands ----");
		sender.sendMessage(Settings.PLTAG + ChatColor.RED + "/et reload" + ChatColor.RESET + " - Reloads the configuration files.");
		sender.sendMessage(Settings.PLTAG + ChatColor.RED + "/et help" + ChatColor.RESET + " - Displays this page.");
		sender.sendMessage(Settings.PLTAG + ChatColor.RED + "/et update" + ChatColor.RESET + " - (Console command) Updates the plugin.");
		sender.sendMessage(Settings.PLTAG + ChatColor.BLUE + "Evil Tongue v1.0 plugin by Progr4mm3r");
		sender.sendMessage(Settings.PLTAG + ChatColor.BLUE + "http://www.tolgaozgun.me");
	}

}
