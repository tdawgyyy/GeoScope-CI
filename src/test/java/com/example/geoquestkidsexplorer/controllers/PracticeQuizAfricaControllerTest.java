package com.example.geoquestkidsexplorer.controllers;

import com.example.geoquestkidsexplorer.models.QuizQuestions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class PracticeQuizAfricaControllerTest {
    private static QuizQuestions q( String countryCode, String questionText, List<String> choices,
                                    String correctAnswer, String funFact)
    {
        return new QuizQuestions(countryCode, questionText, choices, correctAnswer, funFact);
    }

    @Test
    void correctSelectionReturnsScoreDeltaOne(){
        var controller = new PracticeQuizAfricaController();
        var question = q("NG", "Capital of Nigeria?", List.of("Lagos", "Abuja", "Kano",
                "Ibadan"), "Abuja","Abuja became capital in 1991.");

        var result = controller.evaluateSelection("Abuja", question);

        assertTrue(result.correct());
        assertEquals(1, result.scoreDelta());
        assertEquals("Abuja", result.correctAnswer());
        assertEquals("Abuja became capital in 1991.", result.funFact());
    }

    @Test
    void wrongSelectionReturnsScoreAndProvidesCorrectAnswer(){
        var controller = new PracticeQuizAfricaController();
        var question = q("EG", "River through Egypt?", List.of("Congo",
                "Niger","Zambezi","Nile"), "Nile","The Nile is 6,650 km long.");

        var result = controller.evaluateSelection("Congo",question);

        assertFalse(result.correct());
        assertEquals(0, result.scoreDelta());
        assertEquals("Nile", result.correctAnswer());
        assertEquals("The Nile is 6,650 km long.", result.funFact());
    }

    @Test
    void counterFormatForQuestionsNo(){
        assertEquals("Question 1 of 5", PracticeQuizAfricaController.formatCounter(0, 5));
        assertEquals("Question 5 of 5", PracticeQuizAfricaController.formatCounter(4, 5));
    }
}