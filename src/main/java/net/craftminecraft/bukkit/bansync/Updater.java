package net.craftminecraft.bukkit.bansync;

import java.io.File;

import org.bukkit.plugin.Plugin;

/*
 * Update library for CraftMinecraft Development Plugins
 * 
 * This class provides means to safetly and easily update a plugin, or check to see if it is updated using api.craftminecraft.net
 * 
 * Based on the original code from H31IX
 * https://github.com/h31ix/Updater/blob/master/src/net/h31ix/updater/Updater.java
 */

public class Updater {
	private Plugin plugin;
	private UpdateType type;
	private File file; // The plugin's file
	private boolean announce; // Whether to announce file downloads
	
	/**
     * Gives the dev the result of the update process. Can be obtained by called getResult().
     */
	public enum UpdateResult
	{
        /**
         * The updater found an update, and has readied it to be loaded the next time the server restarts/reloads.
         */
        SUCCESS,
        /**
         * The updater did not find an update, and nothing was downloaded.
         */
        NO_UPDATE,
        /**
         * The updater found an update, but was unable to download it.
         */
        FAIL_DOWNLOAD,
        /**
         * For some reason, the updater was unable to contact dev.bukkit.org to download the file.
         */
        FAIL_DBO,
        /**
         * When running the version check, the file on DBO did not contain the a version in the format 'vVersion' such as 'v1.0'.
         */
        FAIL_NOVERSION,
        /**
         * The slug provided by the plugin running the updater was invalid and doesn't exist on DBO.
         */
        FAIL_BADSLUG,
        /**
         * The updater found an update, but because of the UpdateType being set to NO_DOWNLOAD, it wasn't downloaded.
         */
        UPDATE_AVAILABLE
	}
	
    /**
     * Allows the dev to specify the type of update that will be run.
     */
    public enum UpdateType
    {
        /**
         * Run a version check, and then if the file is out of date, download the newest version.
         */
        DEFAULT,
        /**
         * Don't run a version check, just find the latest update and download it.
         */
        NO_VERSION_CHECK,
        /**
         * Get information about the version and the download size, but don't actually download anything.
         */
        NO_DOWNLOAD
    }
    
    /**
     * Initialize the updater
     *
     * @param plugin
     *            The plugin that is checking for an update.
     * @param slug
     *            The dev.bukkit.org slug of the project (http://dev.bukkit.org/server-mods/SLUG_IS_HERE)
     * @param file
     *            The file that the plugin is running from, get this by doing this.getFile() from within your main class.
     * @param type
     *            Specify the type of update this will be. See {@link UpdateType}
     * @param announce
     *            True if the program should announce the progress of new updates in console
     */
    public Updater(Plugin plugin, String slug, File file, UpdateType type, boolean announce)
    {
    	this.plugin = plugin;
        this.type = type;
        this.announce = announce;
        this.file = file;
        
    }
}
