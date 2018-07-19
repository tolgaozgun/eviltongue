package me.tolgaozgun.eviltongue.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.tolgaozgun.eviltongue.TongueMain;

public class Logger {

	private static File logsFile;
	
	private static TongueMain plugin = TongueMain.getPlugin(TongueMain.class);
	
	public static void load() {
		logsFile = plugin.getLogsFile();
		
	}
	
	public static void logTagged(UUID uuid) {
		Player p = plugin.getServer().getPlayer(uuid);
		String name = p.getDisplayName();
		writeIntoLogs(name + " (" + uuid.toString() + ") is now the chosen one.");
	}
	
	public static void logKilled(UUID killer, UUID victim) {
		Player vic = plugin.getServer().getPlayer(victim);
		String vicname = vic.getDisplayName();
		Player kil = plugin.getServer().getPlayer(killer);
		String kilname = kil.getDisplayName();
		writeIntoLogs(kilname +" (" + killer.toString() + ") killed " + vicname + " (" + victim.toString() + ")");
	}
	
	public static void logEnded(UUID uuid) {
		Player p = plugin.getServer().getPlayer(uuid);
		String name = p.getDisplayName();
		writeIntoLogs(name + " (" + uuid.toString() + ") is no longer the chosen one!");
	}
	
	public static void logQuit(UUID uuid, String playername) {
		writeIntoLogs(playername + " (" + uuid.toString() + ") has quit, so is no longer the chosen one!");
		
	}
	public static void logNotOnline(UUID uuid,String playername) {
		writeIntoLogs(playername + " (" + uuid.toString() + ") is not online, so is no longer the chosen one!");
		
	}
	
	private static void writeIntoLogs(String string) {

		try {
			FileWriter fw = new FileWriter(logsFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			FileReader fr = new FileReader(logsFile);
			BufferedReader br = new BufferedReader(fr);
			Date time = Calendar.getInstance().getTime();
			bw.write(time + " | " + string);
			bw.newLine();
			fw.flush();
			bw.close();
			fr.close();
			br.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
