package net.mouazkaadan.inshort.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.mouazkaadan.inshort.data.local.Categories
import net.mouazkaadan.inshort.presentation.categories.CategoriesScreen
import net.mouazkaadan.inshort.presentation.newsPage.NewsDetailsScreen
import net.mouazkaadan.inshort.presentation.newsPage.NewsViewModel

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
        ) { entry ->
            val viewModel: NewsViewModel = hiltViewModel()
            val category = entry.arguments?.getString("category") ?: Categories.categoriesList.first().value
            viewModel.getNews(
                category
            )
            NewsDetailsScreen(viewModel, navController, category)
        }
    }
}
