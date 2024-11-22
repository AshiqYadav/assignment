package com.example.assignment.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.Models.Data
import com.example.assignment.Respository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject



@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val myRepository: MyRepository) : ViewModel() {
    private val _uiState = MutableLiveData<Result>()
    val uiState = _uiState

    private val _homeScreenData = MutableStateFlow(Data())
    val homeScreenData = _homeScreenData.asStateFlow()

    private val _dateText = MutableStateFlow("Today")
    val dateText = _dateText.asStateFlow()

    private val _dayText = MutableStateFlow("")
    val dayText = _dayText.asStateFlow()


    val TAG = "HomeScreenViewModel"

    init {
        viewModelScope.launch {
            getHomeScreenData()
            fetchCurrentDate()
        }
    }

    private suspend fun getHomeScreenData() {
        _uiState.value = Result.Loading
        try {
            val response = myRepository.getHomePageData()
            if (response != null && response.success) {
                _homeScreenData.value = response.data
            }
            _uiState.value = Result.Success
        } catch (e: Exception) {
            Log.d(TAG, "getFoodInfoData: ${e.message}")
            _uiState.value = Result.Error(e.message.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchCurrentDate() {
        _uiState.value = Result.Loading
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val dayName = currentDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
            val formattedDate = currentDate.format(
                DateTimeFormatter.ofPattern("MMM", Locale.getDefault())
            )
            val ordinalSuffix = getOrdinalSuffix(currentDate.dayOfMonth)
            _dayText.value = "$dayName, ${currentDate.dayOfMonth}$ordinalSuffix $formattedDate"
            _uiState.value = Result.Success
        }
    }

    private fun getOrdinalSuffix(day: Int): String {
        return when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
    }

}

sealed class Result {
    data class Error(val message: String) : Result()
    data object Success : Result()
    data object Loading : Result()
}