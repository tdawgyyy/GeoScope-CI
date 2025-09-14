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

class PracticeQuizOceaniaControllerTest {
    private static QuizQuestions q( String countryCode, String questionText, List<String> choices,
                                    String correctAnswer, String funFact)
    {
        return new QuizQuestions(countryCode, questionText, choices, correctAnswer, funFact);
    }

    //Test if no selection is made
    @Test
    void nullSelection(){
        var controller = new PracticeQuizOceaniaController();
        var question = q("AU", "What is the capital city of Australia?",
                Arrays.asList("Sydney", "Melbourne", "Canberra", "Brisbane"), "Canberra",
                "Canberra is a planned city, not just a large metropolis that grew over time.");

        var r = controller.evaluateSelection(null, question);

        assertFalse(r.correct());
        assertEquals(0, r.scoreDelta());
        assertEquals("Canberra",r.correctAnswer());
    }

    // test for correct choices returning the correct answer/ point
    @Test
    void correctSelectionReturnsScoreDeltaOne(){
        var controller = new PracticeQuizOceaniaController();
        var question = q("AU", "Which country is home to the Great Barrier Reef?",
                Arrays.asList("New Zealand", "Fiji", "Australia", "Papua New Guinea"), "Australia",
                "The Great Barrier Reef is so large it can be seen from outer space.");

        // Intentionally put input that act as the user selecting the correct answer
        var result = controller.evaluateSelection("Australia", question);

        //Asserts should mark it correct, add the correct point (+1), and correct fun fact data
        assertTrue(result.correct());
        assertEquals(1, result.scoreDelta());
        assertEquals("Australia", result.correctAnswer());
    }

    //Test when a wrong answer is chosen, keeping the fun fact visible
    @Test
    void wrongAnswerSelection(){
        var controller = new PracticeQuizOceaniaController();
        var question = q("PG", "What is the largest island in Oceania?",
                Arrays.asList("Tasmania", "New Guinea", "South Island", "Borneo"), "New Guinea",
                "New Guinea is the world's second-largest island, after Greenland.");

        // Act as a user choosing the wrong answer
        var result = controller.evaluateSelection("Tasmania",question);

        assertFalse(result.correct()); //Mark answer as incorrect
        assertEquals(0, result.scoreDelta()); // Score should not be changed
        assertEquals("New Guinea", result.correctAnswer()); // Provide the correct answer
    }
}