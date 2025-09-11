package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class OceaniaController {

    @FXML
    private void handleLearn(ActionEvent event) {
        System.out.println("User chose to learn about Oceania.");
        // TODO: Navigate to Learn page for Oceania
    }

    @FXML
    private void handleTest(ActionEvent event) {
        System.out.println("User chose to test their knowledge of Oceania.");
        // TODO: Navigate to Test page for Oceania
    }


    @FXML
    private void handleBackToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
