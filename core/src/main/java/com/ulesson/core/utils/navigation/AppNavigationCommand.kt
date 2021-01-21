package com.ulesson.core.utils.navigation

import androidx.navigation.NavDirections

sealed class AppNavigationCommand {
    data class ToDestination(val direction: NavDirections): AppNavigationCommand()
    object Back: AppNavigationCommand()
}
