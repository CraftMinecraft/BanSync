package com.craftminecraft.bansync.log;

import com.craftminecraft.bansync.BanSync;

public class Logger {
	private BanSync bansyncinterface = null;
	
	public Logger (BanSync p) {
		bansyncinterface = p;
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
