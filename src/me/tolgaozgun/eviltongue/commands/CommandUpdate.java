package me.tolgaozgun.eviltongue.commands;

import org.bukkit.command.CommandSender;

import me.tolgaozgun.eviltongue.TongueMain;
import me.tolgaozgun.eviltongue.util.Settings;
import me.tolgaozgun.eviltongue.util.Updater;
import me.tolgaozgun.eviltongue.util.Updater.ReleaseType;

public class CommandUpdate {
	
	private static TongueMain plugin = TongueMain.getPlugin(TongueMain.class);

	public static void main(CommandSender sender) {


		Updater updater = new Updater(plugin, 298453, plugin.getTheFile(), Updater.UpdateType.DEFAULT,
				false);
		boolean update = updater.getResult() == Updater.UpdateResult.SUCCESS;
		if (update) {
			String name = updater.getLatestName();
			ReleaseType type = updater.getLatestType();
			plugin.getServer().getConsoleSender()
					.sendMessage(Settings.PLTAG + "Successfully updated to version " + type + " " + name);
		} else {

			plugin.getServer().getConsoleSender().sendMessage(Settings.PLTAG + "Update unsuccessful.");
		}
	}
}
