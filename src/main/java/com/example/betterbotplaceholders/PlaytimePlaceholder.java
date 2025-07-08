package com.example.betterbotplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaytimePlaceholder extends PlaceholderExpansion {
    private final PlaytimeTracker plugin;
    private final PlaytimeCache cache;
    
    public PlaytimePlaceholder(PlaytimeTracker plugin, PlaytimeCache cache) {
        this.plugin = plugin;
        this.cache = cache;
    }
    
    @Override
    public @NotNull String getIdentifier() {
        return "playtime";
    }
    
    @Override
    public @NotNull String getAuthor() {
        return "YourName";
    }
    
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        // Handle top placeholders
        if (params.startsWith("topname_")) {
            try {
                int position = Integer.parseInt(params.substring("topname_".length()));
                return cache.getTopName(position);
            } catch (NumberFormatException e) {
                return "Invalid position";
            }
        } else if (params.startsWith("toptime_")) {
            try {
                int position = Integer.parseInt(params.substring("toptime_".length()));
                return cache.getTopTime(position);
            } catch (NumberFormatException e) {
                return "Invalid position";
            }
        }
        
        return null;
    }
}