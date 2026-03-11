package com.practical.assessment.ui.pin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PinRoute

internal fun NavGraphBuilder.pinScreenDestination(
    navController: NavController
) {
    composable<PinRoute> {
        PinScreen()
    }
}

fun NavHostController.navToPin() {
    navigate(PinRoute)
}