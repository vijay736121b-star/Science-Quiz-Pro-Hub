package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: QuizRecord): Long

    @Query("SELECT * FROM quiz_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<QuizRecord>>

    @Query("SELECT * FROM quiz_records ORDER BY score DESC, timestamp DESC LIMIT 1")
    fun getBestRecord(): Flow<QuizRecord?>

    @Query("SELECT * FROM quiz_records ORDER BY timestamp DESC LIMIT 1")
    fun getLatestRecord(): Flow<QuizRecord?>

    @Query("DELETE FROM quiz_records")
    suspend fun clearHistory()
}
