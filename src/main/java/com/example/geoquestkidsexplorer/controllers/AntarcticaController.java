package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

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
}