package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_records")
data class QuizRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val score: Int,
    val totalQuestions: Int,
    val percentage: Float,
    val grade: String,
    val correctCount: Int,
    val incorrectCount: Int,
    val unansweredCount: Int,
    val timerDurationSeconds: Int
)
