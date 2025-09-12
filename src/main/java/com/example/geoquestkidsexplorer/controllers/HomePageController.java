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
        levelsCompletedLabel.setText("0/54");
    }

    /**
     * Handles the "Start Your New Adventure!" button action.
     * Note: This method should be called from the HomePageController itself.
     */
    private void openQuiz(String continent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/geoquestkidsexplorer/quiz_view.fxml"));
            Parent root = loader.load();

            // Show quiz in a new window (keeps Home open)
            Stage quizStage = new Stage();
            quizStage.setTitle(continent + " Quiz");
            quizStage.setScene(new Scene(root, 800, 600));

            // Pass data into the quiz controller
            QuizController controller = loader.getController();
            controller.setStage(quizStage);       // so Back can close this window
            controller.setContinent(continent);   // loads the first question

            quizStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startNewAdventure(ActionEvent event) {
        openQuiz("Oceania"); // or choose based on the user’s level/last choice
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