package com.practical.assessment.ui.checkText

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CheckTextRoute

internal fun NavGraphBuilder.checkTextScreenDestination(
    navController: NavController, onNavToPin: () -> Unit
) {
    composable<CheckTextRoute> {
        CheckTextScreen(onNavToNext = onNavToPin)
    }
}

fun NavHostController.navToCheckText() {
    navigate(CheckTextRoute){
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}