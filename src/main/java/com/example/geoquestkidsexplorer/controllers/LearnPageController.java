package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LearnPageController {

    @FXML
    private Label regionLabel;

    @FXML
    private ImageView countryImageView;



    @FXML
    private Label countryNameLabel;

    @FXML
    private Label backTextLabel;


    @FXML
    private StackPane flashcardStack;

    private static final String DATABASE_URL = "jdbc:sqlite:geoquest.db";

    // To store countries for the region
    private List<Country> countries = new ArrayList<>();
    private int currentIndex = 0;

    // Track front (image) or back (text) side showing
    private boolean isFront = true;

    public void setRegion(String region) {
        regionLabel.setText("Let's learn about " + region);
        loadCountriesForRegion(region);

        if (!countries.isEmpty()) {
            showCountry(countries.get(0));
        }
    }

    // Load all countries for the region from DB
    private void loadCountriesForRegion(String region) {
        countries.clear();
        currentIndex = 0;
        isFront = true;

        String sql = "SELECT country, country_picture FROM countries WHERE continent = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, region);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("country");
                byte[] imgBytes = rs.getBytes("country_picture");
                Image img = null;
                if (imgBytes != null && imgBytes.length > 0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
                    img = new Image(bis);
                }
                countries.add(new Country(name, img));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show the image side of a country
    private void showCountry(Country country) {
        // Clear the back text when showing front side
        backTextLabel.setText("");

        countryImageView.setImage(country.image);

        // Show front (image) side only
        countryImageView.setVisible(true);
        backTextLabel.setVisible(false);
        isFront = true;
    }


    // Handle Next button: flip or next card
    @FXML
    private void handleNextCountry(ActionEvent event) {
        if (countries.isEmpty()) return;

        if (isFront) {
            // Flip to back side
            Country current = countries.get(currentIndex);
            backTextLabel.setText("This is " + current.name);
            backTextLabel.setVisible(true);
            countryImageView.setVisible(false);
            isFront = false;
        } else {
            // Move to next country and show front side
            currentIndex = (currentIndex + 1) % countries.size();
            showCountry(countries.get(currentIndex));
        }
    }

    // Back to home button
    @FXML
    private void handleBackToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    // Helper inner class for country data
    private static class Country {
        String name;
        Image image;

        Country(String name, Image image) {
            this.name = name;
            this.image = image;
        }
    }
}
