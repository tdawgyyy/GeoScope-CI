package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class AntarcticaController {

    @FXML
    private void handleLearn(ActionEvent event) {
        System.out.println("User chose to learn about Antarctica.");
        // Optionally switch to a Learn page
    }

    @FXML
    private void handleTest(ActionEvent event) {
        System.out.println("User chose to test their knowledge.");
        // Optionally switch to a Test page
    }

    @FXML
    private void handleBackToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }




}