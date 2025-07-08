package com.example.betterbotplaceholders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private Connection connection;
    private final String host, port, database, username, password;
    private final Logger logger;
    
    public DatabaseManager(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.logger = Logger.getLogger("Better-BOT-Placeholders");
        connect();
    }
    
    private void connect() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false",
                username,
                password
            );
            logger.log(Level.INFO, "Connected to database successfully!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to database: " + e.getMessage());
        }
    }
    
    public List<PlaytimeRecord> getTopPlaytimes(int limit) {
        List<PlaytimeRecord> records = new ArrayList<>();
        String query = "SELECT name, time FROM BungeeOnlineTime ORDER BY time DESC LIMIT ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("name");
                long time = rs.getLong("time");
                records.add(new PlaytimeRecord(name, time));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Failed to fetch top playtimes: " + e.getMessage());
        }
        
        return records;
    }
    
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Failed to close database connection: " + e.getMessage());
        }
    }
    
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}