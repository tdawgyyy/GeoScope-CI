package com.example.geoquestkidsexplorer.controllers;

import com.example.geoquestkidsexplorer.database.DatabaseManager;
import com.example.geoquestkidsexplorer.models.UserProfile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppController {

    @FXML
    private Label scoreLabel;
    @FXML
    private VBox sideBarVBox;
    @FXML
    private VBox contentVBox;

    private String explorerName;
    private String selectedAvatar;

    @FXML
    public void initialize() {
        // First, check for an existing user profile
        UserProfile userProfile = DatabaseManager.getUserProfile();

        if (userProfile != null) {
            // If a profile exists, load the main content directly
            this.explorerName = userProfile.getExplorerName();
            this.selectedAvatar = userProfile.getAvatar();
            loadHomePage();
        } else {
            // If no profile exists, load the profile creation scene
            loadProfileCreationScene();
        }

        // Add these event handlers back
        sideBarVBox.setOnMouseEntered(event -> expandSideBar());
        sideBarVBox.setOnMouseExited(event -> collapseSideBar());
    }

    private void expandSideBar() {
        sideBarVBox.setPrefWidth(200.0);
        // Hide the icons in the collapsed state and show them when expanded
        sideBarVBox.getChildren().forEach(node -> {
            if (node instanceof VBox) {
                VBox iconContainer = (VBox) node;
                iconContainer.getChildren().forEach(child -> {
                    if (child instanceof HBox) {
                        HBox iconHBox = (HBox) child;
                        iconHBox.getChildren().forEach(iconChild -> {
                            if (iconChild.getStyleClass().contains("nav-icon-text")) {
                                iconChild.setOpacity(1.0);
                            }
                        });
                    }
                });
            }
        });
    }

    private void collapseSideBar() {
        sideBarVBox.setPrefWidth(60.0);
        // Hide the text when collapsing
        sideBarVBox.getChildren().forEach(node -> {
            if (node instanceof VBox) {
                VBox iconContainer = (VBox) node;
                iconContainer.getChildren().forEach(child -> {
                    if (child instanceof HBox) {
                        HBox iconHBox = (HBox) child;
                        iconHBox.getChildren().forEach(iconChild -> {
                            if (iconChild.getStyleClass().contains("nav-icon-text")) {
                                iconChild.setOpacity(0.0);
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * Called by the ProfileCreatedController to set the initial user data.
     * This method then loads the home page.
     */
    public void setProfileData(String explorerName, String selectedAvatar) {
        this.explorerName = explorerName;
        this.selectedAvatar = selectedAvatar;
        loadHomePage();
    }

    private void loadProfileCreationScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/profile_creation.fxml"));
            Parent root = loader.load();

            // The `MainApp` class might need to handle the initial scene setting
            Stage stage = (Stage) contentVBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Navigation Methods ---
    @FXML
    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
            Parent content = loader.load();

            // Get the HomePageController and set the data
            HomePageController homePageController = loader.getController();
            homePageController.setProfileData(this.explorerName, this.selectedAvatar);

            // Clear previous content and add the new one
            contentVBox.getChildren().clear();
            contentVBox.getChildren().add(content);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load homepage. Check your FXML file path.");
        }
    }

    public void updateScore(int newScore) {
        if (scoreLabel != null) {
            scoreLabel.setText(String.valueOf(newScore));
        }
    }

    @FXML
    private void loadFunFacts() {
        loadContent("/com/example/geoquestkidsexplorer/funfacts.fxml");
    }

    @FXML
    private void loadMyProgress() {
        // You would need to pass data to this controller as well
        loadContent("/com/example/geoquestkidsexplorer/userprogress.fxml");
    }

    private void loadContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            contentVBox.getChildren().clear();
            contentVBox.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load content from " + fxmlPath);
        }
    }
}
