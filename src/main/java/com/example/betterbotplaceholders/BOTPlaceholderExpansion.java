package com.example.betterbotplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class BOTPlaceholderExpansion extends PlaceholderExpansion {
    private final BetterBOTPlaceholders plugin;
    private final PlaytimeCache cache;
    
    public BOTPlaceholderExpansion(BetterBOTPlaceholders plugin, PlaytimeCache cache) {
        this.plugin = plugin;
        this.cache = cache;
    }
    
    @Override
    public @NotNull String getIdentifier() {
        return "bot";
    }
    
    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }
    
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        try {
            // Leaderboard placeholders
            if (params.startsWith("topname_")) {
                return handleTopName(params);
            }
            else if (params.startsWith("toptime_totalseconds_")) {
                return handleTotalSeconds(params);
            }
            else if (params.startsWith("toptime_totalminutes_")) {
                return handleTotalMinutes(params);
            }
            else if (params.startsWith("toptime_totalhours_")) {
                return handleTotalHours(params);
            }
            else if (params.startsWith("toptime_totaldays_")) {
                return handleTotalDays(params);
            }
            else if (params.startsWith("toptime_seconds_")) {
                return handleSecondsComponent(params);
            }
            else if (params.startsWith("toptime_minutes_")) {
                return handleMinutesComponent(params);
            }
            else if (params.startsWith("toptime_hours_")) {
                return handleHoursComponent(params);
            }
            else if (params.startsWith("toptime_days_")) {
                return handleDaysComponent(params);
            }
            else if (params.startsWith("toptime_formatted_")) {
                return handleFormattedTime(params);
            }
            // Player placeholders
            else if (params.equals("player_totalseconds") && player != null) {
                return handlePlayerTotalSeconds(player);
            }
            else if (params.equals("player_seconds") && player != null) {
                return handlePlayerSecondsComponent(player);
            }
            else if (params.equals("player_totalminutes") && player != null) {
                return handlePlayerTotalMinutes(player);
            }
            else if (params.equals("player_minutes") && player != null) {
                return handlePlayerMinutesComponent(player);
            }
            else if (params.equals("player_totalhours") && player != null) {
                return handlePlayerTotalHours(player);
            }
            else if (params.equals("player_hours") && player != null) {
                return handlePlayerHoursComponent(player);
            }
            else if (params.equals("player_totaldays") && player != null) {
                return handlePlayerTotalDays(player);
            }
            else if (params.equals("player_days") && player != null) {
                return handlePlayerDaysComponent(player);
            }
            else if (params.equals("player_formatted") && player != null) {
                return handlePlayerFormattedTime(player);
            }
        } catch (Exception e) {
            return "Error";
        }
    return null;
}

    // Handle top name for leaderboard
    private String handleTopName(String params) {
        try {
            int position = Integer.parseInt(params.replace("topname_", ""));
            return cache.getTopName(position);
        } catch (NumberFormatException e) {
            return "Invalid";
        }
    }

    // Total seconds for leaderboard
    private String handleTotalSeconds(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_totalseconds_", ""));
            long millis = cache.getTopMillis(position);
            return String.valueOf(millis / 1000L); // 1000ms = 1 second
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Total minutes for leaderboard
    private String handleTotalMinutes(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_totalminutes_", ""));
            long millis = cache.getTopMillis(position);
            return String.valueOf(millis / 60000L); // 60,000ms = 1 minute
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Total hours for leaderboard
    private String handleTotalHours(String params) {
        try {
            int position = Integer.parseInt(params.replaceAll(".*?(\\d+).*", "$1"));
            long millis = cache.getTopMillis(position);
            return String.valueOf(millis / (1000 * 60 * 60));
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Total days for leaderboard
    private String handleTotalDays(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_totaldays_", ""));
            long millis = cache.getTopMillis(position);
            return String.valueOf(millis / (1000 * 60 * 60 * 24));
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Days component for leaderboard
    private String handleDaysComponent(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_days_", ""));
            long millis = cache.getTopMillis(position);
            return String.valueOf((millis / (1000 * 60 * 60 * 24)) % 365);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Hours component for leaderboard
    private String handleHoursComponent(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_hours_", ""));
            long millis = cache.getTopMillis(position);
            long totalSeconds = millis / 1000;
            return String.valueOf((totalSeconds / 3600) % 24);  // 3600 seconds in an hour
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Minutes component for leaderboard
    private String handleMinutesComponent(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_minutes_", ""));
            long millis = cache.getTopMillis(position);
            long totalSeconds = millis / 1000;
            return String.valueOf((totalSeconds / 60) % 60);  // 60 seconds in a minute
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Leaderboard seconds component
    private String handleSecondsComponent(String params) {
        try {
            int position = Integer.parseInt(params.replace("toptime_seconds_", ""));
            long millis = cache.getTopMillis(position);
            long totalSeconds = millis / 1000;
            return String.valueOf(totalSeconds % 60);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    // Player total hours
    private String handlePlayerTotalHours(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        return String.valueOf(millis / (1000 * 60 * 60));
    }

    // Player hours component
    private String handlePlayerHoursComponent(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        long totalSeconds = millis / 1000;
        return String.valueOf((totalSeconds / 3600) % 24);
    }

    // Player total minutes
    private String handlePlayerTotalMinutes(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        return String.valueOf(millis / 60000L);
    }

    // Player minutes component
    private String handlePlayerMinutesComponent(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        long totalSeconds = millis / 1000;
        return String.valueOf((totalSeconds / 60) % 60);
    }

    // Player total seconds
    private String handlePlayerTotalSeconds(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        return String.valueOf(millis / 1000L);
    }

    // Player seconds component
    private String handlePlayerSecondsComponent(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        long totalSeconds = millis / 1000;
        return String.valueOf(totalSeconds % 60);  // Seconds component (0-59)
    }

    // Player days component
    private String handlePlayerDaysComponent(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        long totalSeconds = millis / 1000;
        return String.valueOf((totalSeconds / 86400) % 7);
    }

    // Player total days
    private String handlePlayerTotalDays(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        return String.valueOf(millis / 86400000);
    }

    // Formatted time (leaderboard)
    private String handleFormattedTime(String params) {
        int position = Integer.parseInt(params.replace("toptime_formatted_", ""));
        long millis = cache.getTopMillis(position);
        return formatTime(millis);
    }

    // Formatted time (player)
    private String handlePlayerFormattedTime(OfflinePlayer player) {
        long millis = cache.getPlayerMillis(player.getName());
        return formatTime(millis);
    }

    // Universal time formatter
    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours > 0 || days > 0) sb.append(hours).append("h ");
        if (minutes > 0 || hours > 0 || days > 0) sb.append(minutes).append("m ");
        sb.append(secs).append("s");
        return sb.toString().trim();
    }


}