package net.mouazkaadan.inshort.presentation.screen.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.mouazkaadan.inshort.data.local.Categories
import net.mouazkaadan.inshort.presentation.component.CategoriesTopAppBar
import net.mouazkaadan.inshort.presentation.component.CategoryCard
import net.mouazkaadan.inshort.presentation.navigation.Screen

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController) {
    val gridState = rememberLazyGridState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            CategoriesTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(it),
            state = gridState,
            columns = GridCells.Adaptive(140.dp)
        ) {
            items(Categories.categoriesList) { category ->
                CategoryCard(category = category) {
                    navController.navigate(Screen.NewsDetailsScreen.withArgs(category.value))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(navController = rememberNavController())
}
