package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        levelsCompletedLabel.setText("0/54");
    }

    /**
     * Handles the "Start Your New Adventure!" button action.
     * Note: This method should be called from the HomePageController itself.
     */
    @FXML
    private void startNewAdventure(ActionEvent event) {
        // Here you would add the logic to switch to the main game screen,
        // likely by calling a method in the MainController.
        System.out.println("Starting a new adventure!");
    }
}