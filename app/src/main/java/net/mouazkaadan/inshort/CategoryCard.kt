package net.mouazkaadan.inshort

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.mouazkaadan.inshort.presentation.categories.model.CategoryModel

/**
 * @author muaz
 * Created on 9/7/2022.
 */
@Composable
fun CategoryCard(category: CategoryModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(2F),
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