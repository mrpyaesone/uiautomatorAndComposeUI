package com.practical.assessment.ui.login

sealed class LoginUiState{
    data object Idle: LoginUiState()
    data object Success: LoginUiState()
    data class Error(val message: String) : LoginUiState()
}