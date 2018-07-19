package me.tolgaozgun.eviltongue.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import me.tolgaozgun.eviltongue.TongueMain;

public class ConfigHandler {

	private static TongueMain plugin = TongueMain.getPlugin(TongueMain.class);
	private static File configFile;
	private static FileConfiguration config;

	public static void load() {
		configFile = plugin.getConfigFile();
		config = plugin.getConfig();
	}

	public static void saveConfig() {
		if (Settings.TAGGED_PLAYER != null) {
			String playername = plugin.getServer().getPlayer(Settings.TAGGED_PLAYER).getDisplayName();
			config.set("players.tagged.uuid", Settings.TAGGED_PLAYER.toString());
			config.set("players.tagged.playername", playername);
		}else {
			config.set("players.tagged.uuid", null);
			config.set("players.tagged.playername", null);
		}
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConfig() {
		if (config != null) {
			if (config.getString("players.tagged.uuid") != null) {
				Settings.TAGGED_PLAYER = UUID.fromString(config.getString("players.tagged.uuid"));
				
			}
		}
	}
	
	public static void checkIfOnline() {

		if(plugin.getServer().getPlayer(Settings.TAGGED_PLAYER) == null) {
			String playername = config.getString("players.tagged.playername");
			Logger.logNotOnline(Settings.TAGGED_PLAYER,playername);
			Settings.TAGGED_PLAYER = null;
		}
	}

}
