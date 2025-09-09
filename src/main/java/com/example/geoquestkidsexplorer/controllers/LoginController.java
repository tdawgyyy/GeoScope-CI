package com.example.geoquestkidsexplorer.controllers;

import com.example.geoquestkidsexplorer.database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    // ---- Layout panes ----
    @FXML private VBox loginForm;
    @FXML private VBox registerForm;

    // ---- Shared message label ----
    @FXML private Label messageLabel;

    // ---- Login fields ----
    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;

    // ---- Register fields ----
    @FXML private TextField registerUsernameField;
    @FXML private TextField registerEmailField;
    @FXML private ComboBox<String> roleCombo;        // fx:id="roleCombo" in FXML
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> avatarCombo;      // fx:id="avatarCombo" in FXML

    // Optional: store a Stage if the main app calls setStage()
    private Stage stage;
    public void setStage(Stage stage) { this.stage = stage; }

    // Map display label -> compact key for DB
    private final Map<String, String> avatarMap = new HashMap<>() {{
        put("👦 Explorer Boy",     "explorer_boy");
        put("👧 Explorer Girl",    "explorer_girl");
        put("👨‍🎓 Student (Boy)",  "explorer_boy-student");
        put("👩‍🎓 Student (Girl)", "explorer_girl-student");
    }};

    @FXML
    public void initialize() {
        // Default view: Login visible, Register hidden
        togglePanels(false);

        // Ensure options exist programmatically (safe even if FXML already set items)
        if (roleCombo != null && (roleCombo.getItems() == null || roleCombo.getItems().isEmpty())) {
            roleCombo.setItems(FXCollections.observableArrayList("Student", "Teacher"));
        }
        if (avatarCombo != null && (avatarCombo.getItems() == null || avatarCombo.getItems().isEmpty())) {
            avatarCombo.setItems(FXCollections.observableArrayList(
                    "👦 Explorer Boy",
                    "👧 Explorer Girl",
                    "👨‍🎓 Student (Boy)",
                    "👩‍🎓 Student (Girl)"
            ));
        }
    }

    // ===========================
    // Login
    // ===========================
    @FXML
    private void handleLogin(ActionEvent event) {
        String email = text(loginEmailField);
        String password = text(loginPasswordField);

        if (email.isBlank() || password.isBlank()) {
            error("Please enter both email and password.");
            return;
        }

        try {
            boolean ok = DatabaseManager.validateLogin(email, password);
            if (!ok) {
                error("Invalid email or password.");
                return;
            }

            success("Login successful! Redirecting…");
            switchToHome(event);

        } catch (Exception e) {
            e.printStackTrace();
            error("Login error: " + e.getMessage());
        }
    }

    // ===========================
    // Register
    // ===========================
    @FXML
    private void handleRegister(ActionEvent event) {
        String username = text(registerUsernameField);
        String email    = text(registerEmailField);
        String role     = roleCombo == null ? "" : String.valueOf(roleCombo.getValue());
        String password = text(registerPasswordField);
        String confirm  = text(confirmPasswordField);

        String avatarDisplay = avatarCombo == null ? null : avatarCombo.getValue();
        String avatarKey     = avatarDisplay == null ? null : avatarMap.get(avatarDisplay);

        // Basic validation
        if (username.isBlank() || email.isBlank() || role.isBlank() || password.isBlank() || confirm.isBlank()) {
            error("Please fill in all fields.");
            return;
        }
        if (!password.equals(confirm)) {
            error("Passwords do not match.");
            return;
        }
        if (avatarKey == null) {
            error("Please pick an avatar.");
            return;
        }

        try {
            // Duplicate check
            if (DatabaseManager.userExists(username, email)) {
                error("Username or email already exists.");
                return;
            }

            // Insert user: (username, email, password, avatar, level, role)
            DatabaseManager.insertUser(username, email, password, avatarKey, null, role);

            success("Registration successful! Please login.");
            clearRegisterFields();
            switchToLogin(event);

        } catch (Exception e) {
            e.printStackTrace();
            error("Registration error: " + e.getMessage());
        }
    }

    // ===========================
    // Switchers
    // ===========================
    @FXML
    private void switchToRegister(ActionEvent event) {
        togglePanels(true);
        clearMessage();
    }

    @FXML
    private void switchToLogin(ActionEvent event) {
        togglePanels(false);
        clearMessage();
    }

    private void togglePanels(boolean showRegister) {
        if (loginForm != null) {
            loginForm.setVisible(!showRegister);
            loginForm.setManaged(!showRegister);
        }
        if (registerForm != null) {
            registerForm.setVisible(showRegister);
            registerForm.setManaged(showRegister);
        }
    }

    // ===========================
    // Navigation
    // ===========================
    private void switchToHome(ActionEvent event) throws IOException {
        Stage s = (stage != null) ? stage : deriveStage(event);
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/geoquestkidsexplorer/homepage.fxml"));
        Scene scene = new Scene(loader.load(),
                s.getScene() != null ? s.getScene().getWidth() : 1000,
                s.getScene() != null ? s.getScene().getHeight() : 700);
        s.setScene(scene);
        s.show();
    }

    private Stage deriveStage(ActionEvent event) {
        if (event != null && event.getSource() instanceof Node n) {
            return (Stage) n.getScene().getWindow();
        }
        throw new IllegalStateException("Stage is not set and cannot be inferred.");
    }

    // ===========================
    // Helpers
    // ===========================
    private static String text(TextField tf) {
        return (tf == null || tf.getText() == null) ? "" : tf.getText().trim();
    }

    private void clearRegisterFields() {
        if (registerUsernameField != null) registerUsernameField.clear();
        if (registerEmailField != null) registerEmailField.clear();
        if (registerPasswordField != null) registerPasswordField.clear();
        if (confirmPasswordField != null) confirmPasswordField.clear();
        if (roleCombo != null) roleCombo.getSelectionModel().clearSelection();
        if (avatarCombo != null) avatarCombo.getSelectionModel().clearSelection();
    }

    private void error(String msg) {
        if (messageLabel != null) {
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            messageLabel.setText(msg);
        }
    }

    private void success(String msg) {
        if (messageLabel != null) {
            messageLabel.setStyle("-fx-text-fill: #16a34a; -fx-font-size: 14px;");
            messageLabel.setText(msg);
        }
    }

    private void clearMessage() {
        if (messageLabel != null) messageLabel.setText("");
    }
}
