package com.example.geoquestkidsexplorer.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class TestPageController {
    public void setRegion(String region) {
        Label regionLabel = new Label();
        regionLabel.setText("Let's learn about "+ region);
    }

    private void handleBack(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        stage.show();
    } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
