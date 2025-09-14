package com.example.geoquestkidsexplorer.models;

import javafx.scene.image.Image;
import java.util.List;

public record PracticeQuizQuestions(
        String questionText,
        List<String> choices,
        String correctAnswer,
        String funFact,
        Image countryImage
) {}
