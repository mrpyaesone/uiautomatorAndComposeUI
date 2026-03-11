package com.practical.assessment.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableSharedFlow<LoginUiState>()
    val uiState: SharedFlow<LoginUiState> = _uiState.asSharedFlow()

    fun login(password: String) {
        viewModelScope.launch {
            when {
                password == "Test@2026" -> _uiState.emit(LoginUiState.Success)
                password.isBlank() -> _uiState.emit(LoginUiState.Error("Password is empty"))
                else -> _uiState.emit(LoginUiState.Error("Invalid Password!"))
            }
        }
    }
}