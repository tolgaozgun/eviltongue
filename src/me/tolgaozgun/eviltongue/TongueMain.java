package me.tolgaozgun.eviltongue;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.tolgaozgun.eviltongue.commands.CommandsManager;
import me.tolgaozgun.eviltongue.util.ConfigHandler;
import me.tolgaozgun.eviltongue.util.Logger;
import me.tolgaozgun.eviltongue.util.Settings;
import me.tolgaozgun.eviltongue.util.Updater;
import me.tolgaozgun.eviltongue.util.Updater.ReleaseType;

public class TongueMain extends JavaPlugin{
	
	private FileConfiguration config;
	private File configFile,logsFile;
	
	public void onEnable() {
		createFiles();
		Settings.load();
		Logger.load();
		ConfigHandler.load();
		getServer().getPluginCommand("eviltongue").setExecutor(new CommandsManager());
		getServer().getPluginManager().registerEvents(new EventListen(), this);
		ConfigHandler.loadConfig();

		if (config.getBoolean("settings.autoupdatecheck")) {
			Updater updater = new Updater(this, 298453, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			boolean update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
			ReleaseType type = updater.getLatestType();
			String name = updater.getLatestName();
			if (update) {
				getServer().getConsoleSender().sendMessage(
						Settings.PLTAG + ChatColor.RED + "Update for version " + type + " " + name + " available.");
				getServer().getConsoleSender().sendMessage(
						Settings.PLTAG + ChatColor.RED + "Use console command, 'et update' to update the plugin.");
			} else {
				getServer().getConsoleSender().sendMessage(Settings.PLTAG + ChatColor.GREEN + "Your plugin is up to date!");
			}
		} else {
			getServer().getConsoleSender()
					.sendMessage(Settings.PLTAG + ChatColor.YELLOW + "Update check is disabled from config, skipping...");
		}
	}
	public void onDisable() {
		ConfigHandler.saveConfig();
	}

	public File getTheFile() {
		return this.getFile();
	}
	public FileConfiguration getConfig() {
		return this.config;
	}
		
	public File getLogsFile() {
		return this.logsFile;
	}
	public File getConfigFile() {
		return this.configFile;
	}
	
	private void createFiles() {
		configFile = new File(getDataFolder(), "config.yml");
		logsFile = new File(getDataFolder(), "logs.yml");

		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			saveResource("config.yml", true);
		}
		if(!logsFile.exists()) {
			logsFile.getParentFile().mkdirs();
			saveResource("logs.yml",true);
		}

		config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
}
