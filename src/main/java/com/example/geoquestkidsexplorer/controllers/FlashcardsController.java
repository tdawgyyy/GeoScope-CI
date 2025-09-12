package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashcardsController {

    @FXML
    private Label regionLabel;

    @FXML
    private ImageView countryImageView;

    @FXML
    private Label backTextLabel;

    @FXML
    private StackPane flashcardStack;

    private static final String DATABASE_URL = "jdbc:sqlite:geoquest.db";

    private List<Country> countries = new ArrayList<>();
    private int currentIndex = 0;
    private boolean isFront = true;

    // Set region and load countries for it
    public void setRegion(String region) {
        regionLabel.setText("Flashcards for " + region);
        loadCountriesForRegion(region);
        if (!countries.isEmpty()) {
            showCountry(countries.get(0));
        } else {
            backTextLabel.setText("Sorry, no countries yet.");
            countryImageView.setImage(null);
            countryImageView.setVisible(false);
            backTextLabel.setVisible(true);
        }
    }

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

            Collections.shuffle(countries); // random order

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCountry(Country country) {
        // Always show front initially
        countryImageView.setImage(country.image);
        countryImageView.setVisible(true);

        backTextLabel.setText("");
        backTextLabel.setVisible(false);

        isFront = true;
    }

    @FXML
    private void handleNextCountry(ActionEvent event) {
        if (countries.isEmpty()) return;

        if (isFront) {
            // Flip to back side: show name
            Country current = countries.get(currentIndex);
            backTextLabel.setText("This is " + current.name);
            backTextLabel.setVisible(true);

            countryImageView.setVisible(false);

            isFront = false;
        } else {
            // Advance to next country and show front side
            currentIndex = (currentIndex + 1) % countries.size();
            showCountry(countries.get(currentIndex));
        }
    }

    private static class Country {
        String name;
        Image image;

        Country(String name, Image image) {
            this.name = name;
            this.image = image;
        }
    }

    @FXML
    private void handleBackToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/mainapp.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

}
