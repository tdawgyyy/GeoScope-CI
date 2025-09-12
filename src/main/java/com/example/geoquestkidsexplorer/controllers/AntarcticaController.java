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
    private void handleLearn(ActionEvent event) throws IOException {
        System.out.println("User chose to learn about Antarctica.");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/LearnPage.fxml"));
        Parent learnRoot = loader.load();

        LearnPageController learnController = loader.getController();
        learnController.setRegion("Antarctica");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(learnRoot);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleTest(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/testpage.fxml"));
        Parent root = loader.load();

        TestPageController controller = loader.getController();
        controller.setRegion("Antarctica");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleFlashcards(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/FlashcardsPage.fxml"));
        Parent flashcardRoot = loader.load();

        FlashcardsController controller = loader.getController();
        controller.setRegion("Antarctica");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(flashcardRoot);
        stage.setScene(scene);
        stage.show();
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