package com.example.geoquestkidsexplorer.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import java.io.IOException;


public class TestPageController {
    public void setRegion(String region) {
        Label regionLabel = new Label();
        regionLabel.setText("Let's learn about "+ region);
    }

    // Return back to homepage
    @FXML
    private void handleBackToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Swap scene to existing homepage
        stage.setScene(new Scene(root));
        stage.setTitle("GeoScope - Home");
        stage.show();
    }
}
