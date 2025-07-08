package com.example.betterbotplaceholders;

import java.util.ArrayList;
import java.util.List;

public class PlaytimeCache {
    private final BetterBOTPlaceholders plugin;
    private final DatabaseManager databaseManager;
    private List<PlaytimeRecord> topPlaytimes;
    private final int cacheSize = 10;
    
    public PlaytimeCache(BetterBOTPlaceholders plugin, DatabaseManager databaseManager) {
        this.plugin = plugin;
        this.databaseManager = databaseManager;
        this.topPlaytimes = new ArrayList<>();
        refresh();
    }
    
    public void refresh() {
        topPlaytimes = databaseManager.getTopPlaytimes(cacheSize);
    }
    
    public String getTopName(int position) {
        if (position < 1 || position > topPlaytimes.size()) return "N/A";
        return topPlaytimes.get(position - 1).getName();
    }
    
    public long getTopMillis(int position) {
        if (position < 1 || position > topPlaytimes.size()) return 0;
        return topPlaytimes.get(position - 1).getTime();
    }
    
    public long getPlayerMillis(String playerName) {
        return databaseManager.getPlayerPlaytime(playerName);
    }
    
    public int getCacheSize() {
        return cacheSize;
    }
}