package me.tolgaozgun.eviltongue.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.eviltongue.TongueMain;
import me.tolgaozgun.eviltongue.util.Settings;
import net.md_5.bungee.api.ChatColor;

public class CommandsManager implements CommandExecutor {
	TongueMain plugin = TongueMain.getPlugin(TongueMain.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				CommandHelp.main(p);
			} else if (args.length == 1) {
				String message = args[0].toLowerCase();
				if (message.equals("reload")) {
					if (p.hasPermission(Settings.RELOAD_PERMISSION)) {
						CommandReload.main(p);
					}
				} else if (message.equals("help")) {
					if (p.hasPermission(Settings.HELP_PERMISSION)) {
						CommandHelp.main(p);
					}
				}else if (message.equals("update")) {
					p.sendMessage(Settings.PLTAG + ChatColor.RED + "You can only update the plugin via console!");
				}
			}
		} else {
			if (args.length == 0) {
				CommandHelp.main(sender);
			} else if (args.length == 1) {
				String message = args[0].toLowerCase();
				if (message.equals("reload")) {
					CommandReload.main(sender);
				} else if (message.equals("help")) {
					CommandHelp.main(sender);
				} else if (message.equals("update")) {
					CommandUpdate.main(sender);
				}
			}
		}
		return true;
	}
}
