package net.mouazkaadan.inshort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import net.mouazkaadan.inshort.data.local.Categories.categoriesList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gridState = rememberLazyGridState()
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Adaptive(140.dp)
            ) {
                items(categoriesList) { category ->
                    CategoryCard(category = category)
                }
            }
        }
    }
}
