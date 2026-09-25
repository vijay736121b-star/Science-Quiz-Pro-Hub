package com.example.data.model

/**
 * Represents a single science quiz question.
 */
data class Question(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val category: String
)

/**
 * Tracks the user's answer state for a specific question in a quiz session.
 */
data class UserAnswer(
    val questionId: Int,
    val selectedOptionIndex: Int?,
    val isAnswered: Boolean,
    val isCorrect: Boolean,
    val isTimedOut: Boolean
)
