package com.craftminecraft.plugins.bansync.log;

import com.craftminecraft.plugins.bansync.BanSync;

public class Logger {
	public Logger (BanSync p) {

	}
	
	public void log (String message) {
		log(LogLevels.NONE, message);
	}
	
	public void log (LogLevels type, String message) {
		switch (type) {
		case INFO:
			System.out.print("[BanSync] [INFO] " + message);
			break;
		case WARNING:
			System.out.print("[BanSync] [WARNING] " + message);
			break;
        case SEVERE:
            System.out.print("[BanSync] [SEVERE] " + message);
            break;
        case FATAL:
            System.out.print("[BanSync] [FATAL] " + message);
            break;
        default:
            System.out.print("[BanSync] " + message);
            break;
		}
	}
}
