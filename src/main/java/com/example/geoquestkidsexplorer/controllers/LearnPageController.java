package com.example.geoquestkidsexplorer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LearnPageController {

    @FXML
    private Label regionLabel;

    public void setRegion(String region) {
        regionLabel.setText("Let's learn about "+ region);
    }


}
