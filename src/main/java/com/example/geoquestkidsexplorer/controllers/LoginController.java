package com.example.geoquestkidsexplorer.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LoginController {

    @FXML
    private VBox loginForm;

    @FXML
    private VBox registerForm;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField registerFullNameField;

    @FXML
    private TextField registerEmailField;

    @FXML
    private PasswordField registerPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    // Reference to the main stage to allow scene switching
    private Stage stage;

    /**
     * Sets the stage for the controller.
     * This is called by the main application class after the FXML is loaded.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the controller. This method is called automatically after the FXML has been loaded.
     */
    @FXML
    public void initialize() {
        // Initially show the login form and hide the registration form
        loginForm.setVisible(true);
        loginForm.setManaged(true);
        registerForm.setVisible(false);
        registerForm.setManaged(false);
    }

    /**
     * Handles the login button action.
     * @param event The ActionEvent from the button click.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();

        // Placeholder logic for login.
        // In a real app, you would validate credentials against a database.
        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both email and password.");
        } else {
            System.out.println("Attempting login with email: " + email);
            messageLabel.setText("Login successful! Redirecting...");
            // On successful login, redirect to the new profile creation page
            try {
                switchToProfileCreation();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Error redirecting to profile creation.");
            }
        }
    }

    /**
     * Handles the register button action.
     * @param event The ActionEvent from the button click.
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        String fullName = registerFullNameField.getText();
        String email = registerEmailField.getText();
        String password = registerPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Placeholder logic for registration.
        // In a real app, you would create a new user in a database.
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
        } else {
            System.out.println("Attempting registration for: " + fullName);
            messageLabel.setText("Registration successful! Please Login.");
            // After successful registration, switch back to the login form
            switchToLogin(event);
        }
    }

    /**
     * Toggles the view from login to register form.
     * @param event The ActionEvent from the button click.
     */
    @FXML
    private void switchToRegister(ActionEvent event) {
        loginForm.setVisible(false);
        loginForm.setManaged(false);
        registerForm.setVisible(true);
        registerForm.setManaged(true);
        messageLabel.setText("");
    }

    /**
     * Toggles the view from register to login form.
     * @param event The ActionEvent from the button click.
     */
    @FXML
    private void switchToLogin(ActionEvent event) {
        registerForm.setVisible(false);
        registerForm.setManaged(false);
        loginForm.setVisible(true);
        loginForm.setManaged(true);
        messageLabel.setText("");
    }

    /**
     * Switches the scene to the profile creation screen.
     */
    private void switchToProfileCreation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/geoquestkidsexplorer/profilecreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
        stage.show();
    }
}

