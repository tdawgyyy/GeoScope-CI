package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import javafx.scene.control.Label;

public class HomePageController {

    @FXML
    private Label explorerNameLabel;
    @FXML
    private Label explorerAvatarLabel;
//    @FXML
//    private Label scoreLabel; //NOTE:Will be deleted if not needed here.

    @FXML
    private Label perfectScoreLabel;
    @FXML
    private Label correctAnswerLabel;
    @FXML
    private Label currentLevelLabel;
    @FXML
    private Label levelsCompletedLabel;


    /**
     * Sets the profile data (name and avatar) on the dashboard.
     * This method is called by the MainController after the home page FXML is loaded.
     *
     * @param explorerName The name of the explorer.
     * @param selectedAvatar The emoji string for the selected avatar.
     */
    public void setProfileData(String explorerName, String selectedAvatar) {
        explorerNameLabel.setText("Welcome, " + explorerName + "!");
        explorerAvatarLabel.setText(selectedAvatar);

        // Set initial values for the tiles
        //scoreLabel.setText("0"); //NOTE:Will be deleted if not needed.
        perfectScoreLabel.setText("0");
        correctAnswerLabel.setText("100%");
        currentLevelLabel.setText("Current Level");
        levelsCompletedLabel.setText("0/7");
    }

    /**
     * Handles the "Start Your New Adventure!" button action.
     * Note: This method should be called from the HomePageController itself.
     */
    @FXML
    private void startNewAdventure(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/geoquestkidsexplorer/country_image.fxml"));
            Parent root = loader.load();

            // Create a new stage for the country page
            Stage countryStage = new Stage();
            countryStage.setTitle("Country Page");
            countryStage.setScene(new Scene(root, 600, 400));

            // Get controller and pass data
            CountryImageController controller = loader.getController();
            controller.setCountry("Australia", countryStage); // Example country

            countryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleOceaniaClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/geoquestkidsexplorer/oceania.fxml"
        ));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }


    @FXML
    private void handleAntarcticaClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/antarctica.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // no resize, CSS stays the same
        stage.getScene().setRoot(root);
        stage.show();
    }



    @FXML
    private void handleSouthAmericaClick(ActionEvent event) {
        System.out.println("South America has been clicked");
    }

    @FXML
    private void handleNorthAmericaClick(ActionEvent event) {
        System.out.println("North America has been clicked");
    }

    @FXML
    private void handleEuropeClick(ActionEvent event) {
        System.out.println("Europe has been clicked");
    }

    @FXML
    private void handleAsiaClick(ActionEvent event) {
        System.out.println("Asia has been clicked");
    }

    @FXML
    private void handleAfricaClick(ActionEvent event) {
        System.out.println("Africa has been clicked");
    }

}