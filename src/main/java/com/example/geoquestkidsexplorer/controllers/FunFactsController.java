package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FunFactsController {

    /**
     * Handles the "Back to Dashboards" button action.
     * This method will transition the user back to the home page by loading
     * the content into the main window's content area.
     */
    @FXML
    private void backToDashboards(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
        Parent root = loader.load();

        // Get the current stage and set the new root as the scene's content
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }


    /**
     * Placeholder methods for each continent's fun facts.
     * In the future, you can add logic here to load a new screen
     * with facts about the selected continent.
     */
    @FXML
    private void loadAfricaFacts() {
        System.out.println("Loading fun facts for Africa...");
    }

    @FXML
    private void loadAsiaFacts() {
        System.out.println("Loading fun facts for Asia...");
    }

    @FXML
    private void loadEuropeFacts() {
        System.out.println("Loading fun facts for Europe...");
    }

    @FXML
    private void loadNorthAmericaFacts() {
        System.out.println("Loading fun facts for North America...");
    }

    @FXML
    private void loadSouthAmericaFacts() {
        System.out.println("Loading fun facts for South America...");
    }

    @FXML
    private void loadOceaniaFacts() {
        System.out.println("Loading fun facts for Oceania...");
    }

    @FXML
    private void loadAntarcticaFacts() {
        System.out.println("Loading fun facts for Antarctica...");
    }
}
