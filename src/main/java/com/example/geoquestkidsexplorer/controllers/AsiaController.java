package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AsiaController {
    @FXML
    private void backToContinents(ActionEvent event) {
        try {
            // Load the StartAdventure.fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGameModeClick(MouseEvent event) {
        // Get the source of the click, which is the VBox tile
        Node clickedTile = (Node) event.getSource();
        String tileId = clickedTile.getId();

        // Use the ID to determine which game mode was selected
        if ("practiceModeTile".equals(tileId)) {
            try {
                // Load the practice quiz FXML file
                Parent root = FXMLLoader.load(getClass().getResource(
                        "/com/example/geoquestkidsexplorer/practicequizasia.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("testModeTile".equals(tileId)) {
            System.out.println("Test Mode Quiz selected!");
            // TODO: Implement logic to start the Test Mode Quiz
        }
    }

}
