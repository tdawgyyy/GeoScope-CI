package com.example.geoquestkidsexplorer.controllers;

import com.example.geoquestkidsexplorer.database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileCreationController {

    @FXML
    private TextField explorerNameField;
    @FXML
    private Label previewAvatarLabel;
    @FXML
    private Label previewNameLabel;
    @FXML
    private Label messageLabel;

    // Instance variables to keep track of the selected avatar and the last selected tile
    private String selectedAvatarEmoji;
    private VBox lastSelectedTile = null;
    @FXML private ComboBox<String> avatarCombo;
    @FXML private Label avatarPreview;
    /**
     * Initializes the controller. This method is called automatically after the FXML has been loaded.
     */
    @FXML
    public void initialize() {
            if (avatarCombo != null) {
                avatarCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                        "👦 Explorer Boy",
                        "👧 Explorer Girl",
                        "👨‍🎓 Student (Boy)",
                        "👩‍🎓 Student (Girl)"
                ));
                avatarCombo.valueProperty().addListener((obs, oldV, newV) -> {
                    avatarPreview.setText((newV == null || newV.isBlank()) ? "🙂" : newV.split(" ")[0]);
                });
            }
        }
    /**
     * Handles the click on any of the avatar tiles.
     * This method will highlight the selected tile and store the avatar emoji.
     */
    @FXML
    private void selectAvatar(javafx.scene.input.MouseEvent event) {
        // First, check if a tile was previously selected and reset its style.
        if (lastSelectedTile != null) {
            lastSelectedTile.getStyleClass().remove("selected-avatar");
        }

        // Get the VBox (tile) that was clicked.
        VBox currentTile = (VBox) event.getSource();

        // Add the CSS class "selected-avatar" to the current tile to highlight it.
        // This is a robust way to avoid layout shifts.
        currentTile.getStyleClass().add("selected-avatar");

        // Store a reference to the current tile.
        lastSelectedTile = currentTile;

        // Get the emoji from the first child (Label) of the VBox.
        Label avatarLabel = (Label) currentTile.getChildren().get(0);
        selectedAvatarEmoji = avatarLabel.getText();

        // Update the preview avatar label with the selected emoji.
        previewAvatarLabel.setText(selectedAvatarEmoji);

        // Clear any previous error messages.
        messageLabel.setText("");

        System.out.println("Selected avatar: " + selectedAvatarEmoji);
    }

    /**
     * Handles the creation of the explorer profile.
     * It validates the input and transitions to the home page.
     */
    @FXML
    private void createProfile(ActionEvent event) throws IOException {
        String explorerName = explorerNameField.getText();

        if (explorerName.isEmpty()) {
            messageLabel.setText("Please enter a name for your explorer.");
            return;
        }

        if (selectedAvatarEmoji == null || selectedAvatarEmoji.isEmpty()) {
            messageLabel.setText("Please select an avatar.");
            return;
        }

        System.out.println("Creating profile for: " + explorerName + " with avatar " + selectedAvatarEmoji);

        // Call the DatabaseManager to save the new user profile
        //DatabaseManager.insertUser(explorerName, selectedAvatarEmoji);

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Load the Home Page view using the correct path.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/profilecreated.fxml"));
        Parent root = loader.load();

        // Pass the name and avatar data to the Home Page controller.
        ProfileCreatedController profileCreatedController = loader.getController();
        profileCreatedController.setProfileData(explorerName, selectedAvatarEmoji);
        profileCreatedController.setStage(stage);

        // Get the current stage and set the new scene.
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}