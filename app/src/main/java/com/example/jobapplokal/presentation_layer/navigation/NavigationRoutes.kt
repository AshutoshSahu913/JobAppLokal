package com.example.jobapplokal.presentation_layer.navigation

import kotlinx.serialization.Serializable

sealed class NavigationRoutes {

    @Serializable
    data object JobScreen

    @Serializable
    data object JobDetailsScreen

    @Serializable
    data object BookmarkScreen

}

sealed class SubNavigation {
    @Serializable
    data object OtherScreen : SubNavigation()

    @Serializable
    data object BottomNavScreen : SubNavigation()

}