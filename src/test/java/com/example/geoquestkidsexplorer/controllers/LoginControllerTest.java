package com.example.geoquestkidsexplorer.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LoginControllerTest {

    // Registration Unit Testing ----------
    /*
    No DB Calls
    * Verify empty fields -> "Please fill in all fields"
    * Verify Password Mismatch -> "Passwords do not match"
    * Avatar not chosen -> "Please pick an avatar"
     */

    private final LoginController controller = new LoginController();

    @Test
    void showErrorWhenFieldsAreBlank(){
        String error = controller.validateRegistrationInputs(
                "","","","","",""
        );
        assertEquals("Please fill in all the fields", error);
    }

    @Test
    void showsErrorWhenPasswordsDoNotMatch(){
        String error = controller.validateRegistrationInputs("alice","a@example.com","Student",
                "password123","differrent","👧 Explorer Girl");
        assertEquals("Passwords do not match", error);
    }

    @Test
    void showErrorWhenAvatarNotChosen(){
        String error = controller.validateRegistrationInputs(
                "bob","b@example.com","Teacher","password123",
                "password123", ""
        );
        assertEquals("Please pick an avatar", error);
    }

    @Test
    void returnsNullWhenAllInputsValid(){
        String error = controller.validateRegistrationInputs(
                "carol","c@example.com","Teacher","password123",
                "password123","👦 Explorer Boy"
        );
        assertNull(error, "Expected no validation error for valid inputs");
    }

}