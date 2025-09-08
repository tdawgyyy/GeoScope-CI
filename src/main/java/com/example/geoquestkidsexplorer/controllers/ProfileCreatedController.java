//package com.example.geoquestkidsexplorer.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class ProfileCreatedController {
//
//    @FXML
//    private Label explorerNameLabel;
//    @FXML
//    private Label explorerAvatarLabel;
//
//    private String explorerName;
//    private String selectedAvatar;
//
//    /**
//     * This method is called by the previous controller to set the user's data.
//     */
//    public void setProfileData(String name, String avatar) {
//        this.explorerName = name;
//        this.selectedAvatar = avatar;
//        explorerNameLabel.setText(this.explorerName);
//        explorerAvatarLabel.setText(this.selectedAvatar);
//    }
//
//    /**
//     * Handles the "Start Adventure" button action.
//     * This method will transition to the home page.
//     */
//    @FXML
//    private void startAdventure(ActionEvent event) {
//        try {
//            // Load the main application layout
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/mainapp.fxml"));
//            Parent root = loader.load();
//
//            // Get a reference to the MainController
//            MainAppController mainAppController = loader.getController();
//
//            // Pass the user's data to the MainController
//            mainAppController.setProfileData(this.explorerName, this.selectedAvatar);
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Failed to load the main layout. Check your FXML file path.");
//        }
//    }
//}

package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileCreatedController {

    @FXML
    private Label explorerNameLabel;
    @FXML
    private Label explorerAvatarLabel;

    private String explorerName;
    private String selectedAvatar;
    private Stage stage; // Add this field

    /**
     * This method is called by the previous controller to set the user's data.
     */
    public void setProfileData(String name, String avatar) {
        this.explorerName = name;
        this.selectedAvatar = avatar;
        explorerNameLabel.setText(this.explorerName);
        explorerAvatarLabel.setText(this.selectedAvatar);
    }

    // Add a method to receive the stage from the previous controller
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles the "Start Adventure" button action.
     * This method will transition to the home page.
     */
    @FXML
    private void startAdventure(ActionEvent event) {
        try {
            // Load the main application layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/mainapp.fxml"));
            Parent root = loader.load();

            // Get a reference to the MainController
            MainAppController mainAppController = loader.getController();

            // Pass the user's data to the MainController
            mainAppController.setProfileData(this.explorerName, this.selectedAvatar);

            // Use the stored stage to set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the main layout. Check your FXML file path.");
        }
    }
}

