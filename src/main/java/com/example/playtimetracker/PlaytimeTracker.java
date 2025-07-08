package com.example.playtimetracker;

import org.bukkit.plugin.java.JavaPlugin;

public class PlaytimeTracker extends JavaPlugin {
    private DatabaseManager databaseManager;
    private PlaytimePlaceholder placeholderExpansion;
    private PlaytimeCache cache;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        // Initialize database connection
        databaseManager = new DatabaseManager(
            getConfig().getString("database.host"),
            getConfig().getString("database.port"),
            getConfig().getString("database.database"),
            getConfig().getString("database.username"),
            getConfig().getString("database.password")
        );
        
        // Initialize cache
        cache = new PlaytimeCache(databaseManager);
        
        // Register placeholder expansion if PlaceholderAPI is present
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderExpansion = new PlaytimePlaceholder(this, cache);
            placeholderExpansion.register();
        }
        
        // Schedule cache refresh every 5 minutes
        getServer().getScheduler().runTaskTimerAsynchronously(this, cache::refresh, 0, 20 * 60 * 5);
        
        getLogger().info("PlaytimeTracker has been enabled!");
    }
    
    @Override
    public void onDisable() {
        if (placeholderExpansion != null) {
            placeholderExpansion.unregister();
        }
        
        databaseManager.close();
        getLogger().info("PlaytimeTracker has been disabled!");
    }
    
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}