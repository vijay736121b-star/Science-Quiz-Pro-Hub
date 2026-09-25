package com.example

import com.example.data.repository.QuestionRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun verifyQuestionRepositoryContainsExactly51Questions() {
        val questions = QuestionRepository.getQuestions(randomizeQuestions = false, randomizeOptions = false)
        assertEquals("Repository must contain exactly 51 science questions", 51, questions.size)
        assertEquals(51, QuestionRepository.getTotalQuestionCount())
    }

    @Test
    fun verifyAllQuestionsHaveFourOptionsAndValidCorrectIndex() {
        val questions = QuestionRepository.getQuestions(randomizeQuestions = false, randomizeOptions = false)
        for (q in questions) {
            assertEquals("Question ${q.id} must have exactly 4 options", 4, q.options.size)
            assertTrue("Question ${q.id} correctOptionIndex must be between 0 and 3", q.correctOptionIndex in 0..3)
            assertFalse("Question ${q.id} text must not be blank", q.questionText.isBlank())
            assertFalse("Question ${q.id} explanation must not be blank", q.explanation.isBlank())
            assertFalse("Question ${q.id} category must not be blank", q.category.isBlank())
            for (opt in q.options) {
                assertFalse("Question ${q.id} options must not be blank", opt.isBlank())
            }
        }
    }

    @Test
    fun verifyRandomizationPreservesCorrectOptionText() {
        val originalList = QuestionRepository.getQuestions(randomizeQuestions = false, randomizeOptions = false)
        val shuffledList = QuestionRepository.getQuestions(randomizeQuestions = true, randomizeOptions = true)

        assertEquals(51, shuffledList.size)
        for (originalQ in originalList) {
            val shuffledQ = shuffledList.find { it.id == originalQ.id }
            assertNotNull(shuffledQ)
            val originalCorrectText = originalQ.options[originalQ.correctOptionIndex]
            val shuffledCorrectText = shuffledQ!!.options[shuffledQ.correctOptionIndex]
            assertEquals(
                "Shuffled question correct answer text must match original",
                originalCorrectText,
                shuffledCorrectText
            )
        }
    }
}
