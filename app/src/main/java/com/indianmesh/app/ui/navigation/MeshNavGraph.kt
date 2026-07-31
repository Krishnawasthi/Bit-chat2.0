/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.indianmesh.app.ui.screens.chat.ChatScreen
import com.indianmesh.app.ui.screens.discovery.DiscoveryScreen
import com.indianmesh.app.ui.screens.home.HomeScreen
import com.indianmesh.app.ui.MainViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun MeshNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            val isConnected = viewModel.isConnected.collectAsState().value
            val users = viewModel.users.collectAsState().value
            
            HomeScreen(
                isConnected = isConnected,
                peerCount = users.size,
                onNavigateToChat = { peerId ->
                    navController.navigate(Screen.Chat.createRoute(peerId))
                },
                onNavigateToDiscovery = {
                    navController.navigate(Screen.Discovery.route)
                }
            )
        }
        
        composable(route = Screen.Discovery.route) {
            DiscoveryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("peerId") { type = NavType.StringType })
        ) { backStackEntry ->
            val peerId = backStackEntry.arguments?.getString("peerId") ?: ""
            ChatScreen(
                peerId = peerId,
                onNavigateBack = { navController.popBackStack() },
                onSendMessage = { text -> viewModel.sendMessage(peerId, text) }
            )
        }
        
        // Settings screen placeholder
        composable(route = Screen.Settings.route) {
            // SettingsScreen(...)
        }
    }
}
