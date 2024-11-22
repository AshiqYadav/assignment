package com.example.assignment.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.Models.DataX
import com.example.assignment.Respository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodInfoViewModel @Inject constructor(private val myRepository: MyRepository) : ViewModel() {

    private val _uiState = MutableLiveData<Result>()
    val uiState = _uiState

    private val _foodInfo = MutableStateFlow(DataX())
    val foodInfo = _foodInfo.asStateFlow()

    val TAG = "FoodInfoViewModel"

    init {
        viewModelScope.launch {
            getFoodInfoData()
        }
    }

    private suspend fun getFoodInfoData() {
        _uiState.value = Result.Loading
        try {
            val response = myRepository.getFoodInfo()
            if (response != null && response.success) {
                _foodInfo.value = response.data
            }
            _uiState.value = Result.Success
        } catch (e: Exception) {
            Log.d(TAG, "getFoodInfoData: ${e.message}")
            _uiState.value = Result.Error(e.message.toString())
        }
    }
}


