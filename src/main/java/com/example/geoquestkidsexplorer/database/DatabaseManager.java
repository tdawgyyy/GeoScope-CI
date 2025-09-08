package com.example.geoquestkidsexplorer.database;

import com.example.geoquestkidsexplorer.models.UserProfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:geoquest.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
                createNewUserTable(conn);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createNewUserTable(Connection conn) {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "avatar TEXT NOT NULL,"
                + "score INTEGER DEFAULT 0,"
                + "levels_completed INTEGER DEFAULT 0"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("User table created successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void insertUser(String name, String avatar) {
        String sql = "INSERT INTO users(name, avatar) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, avatar);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static UserProfile getUserProfile() {
        String sql = "SELECT name, avatar, score, levels_completed FROM users WHERE id = 1"; // Assuming one user for now

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String name = rs.getString("name");
                String avatar = rs.getString("avatar");
                int score = rs.getInt("score");
                int levelsCompleted = rs.getInt("levels_completed");
                return new UserProfile(name, avatar, score, levelsCompleted);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null; // Return null if no user is found
    }

    public static void printAllUsers() {
        String sql = "SELECT id, name, avatar FROM users";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Avatar: " + rs.getString("avatar"));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving users: " + e.getMessage());
        }
    }
}
