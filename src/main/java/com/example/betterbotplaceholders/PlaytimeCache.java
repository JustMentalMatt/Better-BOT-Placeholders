package com.example.betterbotplaceholders;

import java.util.ArrayList;
import java.util.List;

public class PlaytimeCache {
    private final DatabaseManager databaseManager;
    private List<PlaytimeRecord> topPlaytimes;
    private final int cacheSize = 10; // Top 10 players
    
    public PlaytimeCache(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.topPlaytimes = new ArrayList<>();
        refresh();
    }
    
    public void refresh() {
        topPlaytimes = databaseManager.getTopPlaytimes(cacheSize);
    }
    
    public String getTopName(int position) {
        if (position < 1 || position > topPlaytimes.size()) {
            return "N/A";
        }
        return topPlaytimes.get(position - 1).getName();
    }
    
    public String getTopTime(int position) {
        if (position < 1 || position > topPlaytimes.size()) {
            return "0";
        }
        long hours = topPlaytimes.get(position - 1).getTime() / (1000 * 60 * 60);
        return String.valueOf(hours);
    }
    
    public int getCacheSize() {
        return cacheSize;
    }
}