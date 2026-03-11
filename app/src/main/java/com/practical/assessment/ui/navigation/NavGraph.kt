package com.practical.assessment.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.practical.assessment.ui.checkText.checkTextScreenDestination
import com.practical.assessment.ui.checkText.navToCheckText
import com.practical.assessment.ui.login.loginScreenDestination
import com.practical.assessment.ui.pin.navToPin
import com.practical.assessment.ui.pin.pinScreenDestination

internal fun NavGraphBuilder.navGraph(
    navController: NavHostController
) {
    loginScreenDestination(
        navController = navController,
        onNavigateToCheckScreen = navController::navToCheckText
    )

    checkTextScreenDestination(navController = navController, onNavToPin = navController::navToPin)
    pinScreenDestination(navController = navController)
}