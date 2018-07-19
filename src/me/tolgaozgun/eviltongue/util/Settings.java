package me.tolgaozgun.eviltongue.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.tolgaozgun.eviltongue.TongueMain;

public class Settings {
	private static TongueMain plugin = TongueMain.getPlugin(TongueMain.class);
	public static String USE_PERMISSION, STRING_LOOKUP, STRING_LOOKDOWN, TAGGED_MESSAGE, UNTAGGED_MESSAGE,
			TAGGEDP_MESSAGE, UNTAGGEDP_MESSAGE, DEATH_MESSAGE, DEATHP_MESSAGE, PLTAG, RELOAD_PERMISSION,
			HELP_PERMISSION;
	public static boolean DEATH_REMOVE, MESSAGE_REMOVE, ISDEATH_MESSAGE;
	public static UUID TAGGED_PLAYER;

	public static void load(){
		if (plugin.getConfig() != null) {
			PLTAG = translateColors(plugin.getConfig().getString("strings.plugintag") + " ");
			USE_PERMISSION = plugin.getConfig().getString("settings.usepermission");
			STRING_LOOKUP = translateColors(plugin.getConfig().getString("strings.tagmessage"));
			STRING_LOOKDOWN = translateColors(plugin.getConfig().getString("strings.untagmessage"));
			if (STRING_LOOKUP == STRING_LOOKDOWN) {
				STRING_LOOKDOWN += "plusstr";
			}
			TAGGED_MESSAGE = translateColors(plugin.getConfig().getString("strings.taggedmessage"));
			UNTAGGED_MESSAGE = translateColors(plugin.getConfig().getString("strings.untaggedmessage"));
			TAGGEDP_MESSAGE = translateColors(plugin.getConfig().getString("strings.taggedplayermessage"));
			UNTAGGEDP_MESSAGE = translateColors(plugin.getConfig().getString("strings.untaggedplayermessage"));
			DEATH_REMOVE = plugin.getConfig().getBoolean("settings.tagaway.death");
			MESSAGE_REMOVE = plugin.getConfig().getBoolean("settings.tagaway.untagmessage");
			ISDEATH_MESSAGE = plugin.getConfig().getBoolean("settings.deathmessage");
			DEATH_MESSAGE = translateColors(plugin.getConfig().getString("strings.deathmessage"));
			DEATHP_MESSAGE = translateColors(plugin.getConfig().getString("strings.deathplayermessage"));
			RELOAD_PERMISSION = plugin.getConfig().getString("settings.reloadpermission");
			HELP_PERMISSION = plugin.getConfig().getString("settings.helppermission");
		}
	}

	private static String translateColors(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static void warnAll(String message) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(message);
		}
	}

	public static void warnAllSpecial(String m1, Player p, String m2) {
		for (Player p2 : Bukkit.getOnlinePlayers()) {
			if (p2 == p) {
				p2.sendMessage(Settings.PLTAG + ChatColor.RESET + m2);
			} else {
				p2.sendMessage(Settings.PLTAG + ChatColor.RESET + m1);
			}
		}
		plugin.getServer().getConsoleSender().sendMessage(Settings.PLTAG + ChatColor.RESET + m1);
	}

}
