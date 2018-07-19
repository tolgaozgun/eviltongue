package me.tolgaozgun.eviltongue;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.tolgaozgun.eviltongue.util.ConfigHandler;
import me.tolgaozgun.eviltongue.util.Logger;
import me.tolgaozgun.eviltongue.util.Settings;
import net.md_5.bungee.api.ChatColor;

public class EventListen implements Listener {

	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		if (Settings.TAGGED_PLAYER == null) {
			Player p = e.getPlayer();
			if (p.hasPermission(Settings.USE_PERMISSION)) {
				String message = e.getMessage();
				if (message.contains(Settings.STRING_LOOKUP)) {
					Settings.TAGGED_PLAYER = e.getPlayer().getUniqueId();
					Settings.warnAllSpecial(Settings.TAGGED_MESSAGE, p, Settings.TAGGEDP_MESSAGE);
					Logger.logTagged(p.getUniqueId());
					return;
				}
			}
		} else {
			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			if (uuid.compareTo(Settings.TAGGED_PLAYER) == 0) {
				if (p.hasPermission(Settings.USE_PERMISSION)) {
					String message = e.getMessage();
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						if (message.contains(p2.getDisplayName())) {
							p2.setHealth(0);
							Logger.logKilled(uuid, p2.getUniqueId());
							if (Settings.ISDEATH_MESSAGE) {
								String show = Settings.DEATH_MESSAGE.replace("%player%", p.getDisplayName());
								Settings.warnAllSpecial(show, p2, Settings.DEATHP_MESSAGE);
							}
							return;
						}

					}
					if (message.contains(Settings.STRING_LOOKDOWN)) {
						if (Settings.MESSAGE_REMOVE) {
							Settings.TAGGED_PLAYER = null;
							Settings.warnAllSpecial(Settings.UNTAGGED_MESSAGE, p, Settings.UNTAGGEDP_MESSAGE);
							Logger.logEnded(uuid);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (Settings.DEATH_REMOVE) {
			if (Settings.TAGGED_PLAYER != null) {
				Player p = e.getEntity();
				UUID uuid = p.getUniqueId();
				if (uuid.compareTo(Settings.TAGGED_PLAYER) == 0) {
					Settings.TAGGED_PLAYER = null;
					Settings.warnAllSpecial(Settings.UNTAGGED_MESSAGE, p, Settings.UNTAGGEDP_MESSAGE);
					Logger.logEnded(uuid);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		ConfigHandler.checkIfOnline();
	}
}
