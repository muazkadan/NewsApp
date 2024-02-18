package net.mouazkaadan.inshort.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.mouazkaadan.inshort.presentation.screen.categories.CategoriesScreen
import net.mouazkaadan.inshort.presentation.screen.newsPage.NewsDetailsScreen

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CategoriesScreen.route) {
        composable(route = Screen.CategoriesScreen.route) {
            CategoriesScreen(navController)
        }
        composable(
            route = Screen.NewsDetailsScreen.route + "/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )
        ) {
            NewsDetailsScreen(hiltViewModel(), navController)
        }
    }
}
