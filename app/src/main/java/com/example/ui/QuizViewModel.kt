package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.QuizDatabase
import com.example.data.local.QuizRecord
import com.example.data.model.Question
import com.example.data.model.UserAnswer
import com.example.data.repository.QuestionRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    QUIZ,
    RESULT,
    REVIEW,
    INSTRUCTIONS,
    ABOUT,
    PRIVACY_POLICY,
    HISTORY
}

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val userAnswers: Map<Int, UserAnswer> = emptyMap(),
    val timerDurationSeconds: Int = 10,
    val timerSecondsRemaining: Int = 10,
    val isTimerActive: Boolean = false,
    val showExitConfirmDialog: Boolean = false,
    val latestRecord: QuizRecord? = null
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val quizDao = QuizDatabase.getDatabase(application).quizDao()

    val allHistory: StateFlow<List<QuizRecord>> = quizDao.getAllRecords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bestRecord: StateFlow<QuizRecord?> = quizDao.getBestRecord()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun navigateTo(screen: AppScreen) {
        if (screen == AppScreen.HOME && _uiState.value.isTimerActive) {
            cancelTimer()
        }
        _currentScreen.value = screen
    }

    fun setTimerDuration(seconds: Int) {
        _uiState.value = _uiState.value.copy(
            timerDurationSeconds = seconds,
            timerSecondsRemaining = seconds
        )
    }

    fun startNewQuiz() {
        cancelTimer()
        val questions = QuestionRepository.getQuestions(randomizeQuestions = true, randomizeOptions = true)
        _uiState.value = QuizUiState(
            questions = questions,
            currentIndex = 0,
            userAnswers = emptyMap(),
            timerDurationSeconds = _uiState.value.timerDurationSeconds,
            timerSecondsRemaining = _uiState.value.timerDurationSeconds,
            isTimerActive = false,
            showExitConfirmDialog = false
        )
        _currentScreen.value = AppScreen.QUIZ
        startTimerForCurrentQuestion()
    }

    fun selectOption(optionIndex: Int) {
        val state = _uiState.value
        val currentQuestion = state.questions.getOrNull(state.currentIndex) ?: return
        // Prevent duplicate scoring if already answered
        if (state.userAnswers.containsKey(currentQuestion.id)) return

        cancelTimer()

        val isCorrect = optionIndex == currentQuestion.correctOptionIndex
        val answer = UserAnswer(
            questionId = currentQuestion.id,
            selectedOptionIndex = optionIndex,
            isAnswered = true,
            isCorrect = isCorrect,
            isTimedOut = false
        )

        val updatedAnswers = state.userAnswers.toMutableMap().apply {
            put(currentQuestion.id, answer)
        }

        _uiState.value = state.copy(
            userAnswers = updatedAnswers,
            isTimerActive = false
        )
    }

    private fun startTimerForCurrentQuestion() {
        cancelTimer()
        val state = _uiState.value
        val duration = state.timerDurationSeconds

        // If duration is 0, timer is disabled
        if (duration <= 0) {
            _uiState.value = state.copy(
                isTimerActive = false,
                timerSecondsRemaining = 0
            )
            return
        }

        val currentQuestion = state.questions.getOrNull(state.currentIndex) ?: return
        // Do not run timer if question already answered
        if (state.userAnswers.containsKey(currentQuestion.id)) {
            _uiState.value = state.copy(isTimerActive = false)
            return
        }

        _uiState.value = state.copy(
            timerSecondsRemaining = duration,
            isTimerActive = true
        )

        timerJob = viewModelScope.launch {
            for (sec in duration downTo 1) {
                _uiState.value = _uiState.value.copy(timerSecondsRemaining = sec)
                delay(1000L)
            }
            // Timer expired!
            handleTimerTimeout()
        }
    }

    private fun handleTimerTimeout() {
        val state = _uiState.value
        val currentQuestion = state.questions.getOrNull(state.currentIndex) ?: return
        if (state.userAnswers.containsKey(currentQuestion.id)) return

        // Record as timed out / unanswered
        val timeoutAnswer = UserAnswer(
            questionId = currentQuestion.id,
            selectedOptionIndex = null,
            isAnswered = false,
            isCorrect = false,
            isTimedOut = true
        )

        val updatedAnswers = state.userAnswers.toMutableMap().apply {
            put(currentQuestion.id, timeoutAnswer)
        }

        _uiState.value = state.copy(
            userAnswers = updatedAnswers,
            timerSecondsRemaining = 0,
            isTimerActive = false
        )

        // Automatically move to the next question as required by prompt
        viewModelScope.launch {
            delay(1200L) // Brief pause so user sees time expired
            if (_currentScreen.value == AppScreen.QUIZ) {
                moveToNextQuestion()
            }
        }
    }

    fun moveToNextQuestion() {
        cancelTimer()
        val state = _uiState.value
        val currentQuestion = state.questions.getOrNull(state.currentIndex)

        // If unanswered and user presses Next, mark as unanswered
        var updatedAnswers = state.userAnswers
        if (currentQuestion != null && !state.userAnswers.containsKey(currentQuestion.id)) {
            val skipAnswer = UserAnswer(
                questionId = currentQuestion.id,
                selectedOptionIndex = null,
                isAnswered = false,
                isCorrect = false,
                isTimedOut = false
            )
            updatedAnswers = state.userAnswers.toMutableMap().apply {
                put(currentQuestion.id, skipAnswer)
            }
        }

        if (state.currentIndex < state.questions.size - 1) {
            _uiState.value = state.copy(
                currentIndex = state.currentIndex + 1,
                userAnswers = updatedAnswers
            )
            startTimerForCurrentQuestion()
        } else {
            // Reached the end: Finish Quiz
            _uiState.value = state.copy(userAnswers = updatedAnswers)
            finishQuiz()
        }
    }

    fun moveToPreviousQuestion() {
        cancelTimer()
        val state = _uiState.value
        if (state.currentIndex > 0) {
            _uiState.value = state.copy(
                currentIndex = state.currentIndex - 1,
                isTimerActive = false
            )
            // If the previous question wasn't answered, resume timer; otherwise don't
            val prevQuestion = state.questions.getOrNull(state.currentIndex - 1)
            if (prevQuestion != null && !state.userAnswers.containsKey(prevQuestion.id)) {
                startTimerForCurrentQuestion()
            }
        }
    }

    fun requestExitQuiz() {
        _uiState.value = _uiState.value.copy(showExitConfirmDialog = true)
    }

    fun dismissExitDialog() {
        _uiState.value = _uiState.value.copy(showExitConfirmDialog = false)
    }

    fun confirmExitQuiz() {
        cancelTimer()
        _uiState.value = _uiState.value.copy(showExitConfirmDialog = false)
        _currentScreen.value = AppScreen.HOME
    }

    private fun cancelTimer() {
        timerJob?.cancel()
        timerJob = null
        _uiState.value = _uiState.value.copy(isTimerActive = false)
    }

    fun finishQuiz() {
        cancelTimer()
        val state = _uiState.value
        val total = state.questions.size
        var correctCount = 0
        var incorrectCount = 0
        var unansweredCount = 0

        for (q in state.questions) {
            val ans = state.userAnswers[q.id]
            if (ans == null || (!ans.isAnswered && ans.isTimedOut) || (!ans.isAnswered && ans.selectedOptionIndex == null)) {
                unansweredCount++
            } else if (ans.isCorrect) {
                correctCount++
            } else {
                incorrectCount++
            }
        }

        val percentage = if (total > 0) (correctCount.toFloat() / total) * 100f else 0f
        val grade = when {
            percentage >= 95f -> "A+"
            percentage >= 90f -> "A"
            percentage >= 80f -> "B"
            percentage >= 70f -> "C"
            percentage >= 60f -> "D"
            else -> "Needs Review"
        }

        val record = QuizRecord(
            score = correctCount,
            totalQuestions = total,
            percentage = percentage,
            grade = grade,
            correctCount = correctCount,
            incorrectCount = incorrectCount,
            unansweredCount = unansweredCount,
            timerDurationSeconds = state.timerDurationSeconds
        )

        viewModelScope.launch {
            quizDao.insertRecord(record)
        }

        _uiState.value = state.copy(latestRecord = record)
        _currentScreen.value = AppScreen.RESULT
    }

    fun clearHistory() {
        viewModelScope.launch {
            quizDao.clearHistory()
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }
}
