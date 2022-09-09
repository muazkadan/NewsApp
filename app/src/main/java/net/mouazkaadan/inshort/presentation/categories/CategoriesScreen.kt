package net.mouazkaadan.inshort.presentation.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.mouazkaadan.inshort.data.local.Categories
import net.mouazkaadan.inshort.data.model.CategoryModel
import net.mouazkaadan.inshort.presentation.Screen

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@Composable
fun CategoriesScreen(navController: NavController) {
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
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

@Composable
fun CategoryCard(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onClickCategory: (category: String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(2F)
            .clickable {
                onClickCategory.invoke(category.value)
            },
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.name,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(navController = rememberNavController())
}
