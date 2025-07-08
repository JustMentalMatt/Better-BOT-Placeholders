package com.example.betterbotplaceholders;

public class PlaytimeRecord {
    private final String name;
    private final long time;
    
    public PlaytimeRecord(String name, long time) {
        this.name = name;
        this.time = time;
    }
    
    public String getName() {
        return name;
    }
    
    public long getTime() {
        return time;
    }
}