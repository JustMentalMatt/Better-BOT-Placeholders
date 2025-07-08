package com.example.betterbotplaceholders;

import org.bukkit.plugin.java.JavaPlugin;

public class BetterBOTPlaceholders extends JavaPlugin {
    private DatabaseManager databaseManager;
    private BOTPlaceholderExpansion placeholderExpansion;
    private PlaytimeCache cache;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        databaseManager = new DatabaseManager(
            getConfig().getString("database.host"),
            getConfig().getString("database.port"),
            getConfig().getString("database.database"),
            getConfig().getString("database.username"),
            getConfig().getString("database.password")
        );
        
        cache = new PlaytimeCache(this, databaseManager);
        
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderExpansion = new BOTPlaceholderExpansion(this, cache);
            placeholderExpansion.register();
        }
        
        getServer().getScheduler().runTaskTimerAsynchronously(this, cache::refresh, 0, 20 * 60 * 5);
        getLogger().info("BetterBOTPlaceholders has been enabled!");
    }
    
    @Override
    public void onDisable() {
        if (placeholderExpansion != null) {
            placeholderExpansion.unregister();
        }
        databaseManager.close();
        getLogger().info("BetterBOTPlaceholders has been disabled!");
    }
    
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}