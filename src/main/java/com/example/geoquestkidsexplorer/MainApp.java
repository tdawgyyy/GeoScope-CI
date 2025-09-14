package com.example.geoquestkidsexplorer;

import com.example.geoquestkidsexplorer.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.geoquestkidsexplorer.database.DatabaseManager;

import java.io.IOException;

public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        // This is the CRUCIAL first step. Create the database and table.
        DatabaseManager.createNewDatabase();

        // Corrected path to match the package name
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800); // Set initial scene dimensions
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Pass the stage to the controller
        LoginController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("GeoQuest Kids Explorer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}