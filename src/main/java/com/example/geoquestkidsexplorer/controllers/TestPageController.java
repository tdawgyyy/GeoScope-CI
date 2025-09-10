package com.example.geoquestkidsexplorer.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class TestPageController {

    private void handleBack(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/com/geoquestkidsexplorer/homepage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        stage.show();
    } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
