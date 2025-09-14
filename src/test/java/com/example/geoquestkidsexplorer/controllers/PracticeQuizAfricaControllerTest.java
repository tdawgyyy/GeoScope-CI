package com.example.geoquestkidsexplorer.controllers;

import com.example.geoquestkidsexplorer.models.QuizQuestions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

// PractiseQuizAfrica Unit Testing ----------
    /*
    No DB Calls only from data package
    * Verify empty fields -> "Please fill in all fields"
    * Verify Password Mismatch -> "Passwords do not match"
    * Avatar not chosen -> "Please pick an avatar"

     Assertions used ---------------
    * assertEquals = Asserts that the expected value is equal to the actual value
    * assertTrue = Asserts that the given boolean is correct
    * assertFalse = Assert that the given boolean is false
    */

class PracticeQuizAfricaControllerTest {
    private static QuizQuestions q( String countryCode, String questionText, List<String> choices,
                                    String correctAnswer, String funFact)
    {
        return new QuizQuestions(countryCode, questionText, choices, correctAnswer, funFact);
    }

    //Test if no selection is made
    @Test
    void nullSelection(){
        var controller = new PracticeQuizAfricaController();
        var question = q("EG", "What is the capital of Egypt?",
                Arrays.asList("Cairo", "Nairobi", "Lagos", "Accra"), "Cairo",
                "The Great Pyramid of Giza is the oldest of the Seven Wonders of the Ancient World.");

        var r = controller.evaluateSelection(null, question);

        assertFalse(r.correct());
        assertEquals(0, r.scoreDelta());
        assertEquals("Cairo",r.correctAnswer());
    }

    // test for correct choices returning the correct answer/ point
    @Test
    void correctSelectionReturnsScoreDeltaOne(){
        var controller = new PracticeQuizAfricaController();
        var question = q("NG", "Capital of Nigeria?", List.of("Lagos", "Abuja", "Kano",
                "Ibadan"), "Abuja","Abuja became capital in 1991.");

        // Intentionally put input that act as the user selecting the correct answer
        var result = controller.evaluateSelection("Abuja", question);

        //Asserts should mark it correct, add the correct point (+1), and correct fun fact data
        assertTrue(result.correct());
        assertEquals(1, result.scoreDelta());
        assertEquals("Abuja", result.correctAnswer());
        assertEquals("Abuja became capital in 1991.", result.funFact());
    }

    //Test when a wrong answer is chosen, keeping the fun fact visible
    @Test
    void wrongAnswerSelection(){
        var controller = new PracticeQuizAfricaController();
        var question = q("EG", "River through Egypt?", List.of("Congo",
                "Niger","Zambezi","Nile"), "Nile","The Nile is 6,650 km long.");

        // Act as a user choosing the wrong answer
        var result = controller.evaluateSelection("Congo",question);

        assertFalse(result.correct()); //Mark answer as incorrect
        assertEquals(0, result.scoreDelta()); // Score should not be changed
        assertEquals("Nile", result.correctAnswer()); // Provide the correct answer
        assertEquals("The Nile is 6,650 km long.", result.funFact()); // Fun fact should display
    }
}