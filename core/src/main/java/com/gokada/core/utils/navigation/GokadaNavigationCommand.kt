package com.gokada.core.utils.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class GokadaNavigationCommand {
    data class To(val direction: NavDirections): GokadaNavigationCommand()
    object Back: GokadaNavigationCommand()
    data class BackTo(@IdRes val destinationId: Int): GokadaNavigationCommand()
    object BackToRoot: GokadaNavigationCommand()
}