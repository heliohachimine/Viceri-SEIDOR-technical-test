package com.example.viceriseidorteste.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.viceriseidorteste.presentation.features.details.UserDetailScreen
import com.example.viceriseidorteste.presentation.features.list.UserListScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationEnum.UserList.route,
    ) {
        composable(NavigationEnum.UserList.route) {
            UserListScreen(navController)
        }

        composable(NavigationEnum.UserDetail.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            userId?.let {
                UserDetailScreen(userId)
            }
        }
    }
}