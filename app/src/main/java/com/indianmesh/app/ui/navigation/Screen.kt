/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.ui.navigation

sealed class Screen(val route: String) {
    object Discovery : Screen("discovery")
    object Home : Screen("home")
    object Chat : Screen("chat/{peerId}") {
        fun createRoute(peerId: String) = "chat/$peerId"
    }
    object Settings : Screen("settings")
}
