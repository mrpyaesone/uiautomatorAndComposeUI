package com.practical.assessment.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

internal fun NavGraphBuilder.loginScreenDestination(
    navController: NavController, onNavigateToCheckScreen: () -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(onNavigateToCheckScreen = onNavigateToCheckScreen)
    }
}

fun NavHostController.navToLogin() {
    navigate(LoginRoute)
}