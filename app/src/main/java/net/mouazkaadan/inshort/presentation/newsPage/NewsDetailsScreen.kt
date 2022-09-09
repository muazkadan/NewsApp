package net.mouazkaadan.inshort.presentation.newsPage

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.utils.extensions.asUri
import net.mouazkaadan.inshort.utils.extensions.showToast

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@Composable
fun NewsDetailsScreen(viewModel: NewsViewModel) {
    val context = LocalContext.current

    if (viewModel.uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    viewModel.uiState.errorMessage?.let { errorMessage ->
        context.showToast(errorMessage)
    }
    LazyColumn {
        items(viewModel.uiState.newsItems) { news ->
            NewsCard(newsItem = news)
        }
    }
}

@Composable
fun NewsCard(newsItem: NewsItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                val browserIntent = Intent(Intent.ACTION_VIEW, newsItem.readMoreUrl.asUri())
                context.startActivity(browserIntent, null)
            },
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(text = newsItem.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = newsItem.content, modifier = Modifier.padding(top = 6.dp))
            Text(
                text = buildString {
                    append(newsItem.author)
                    append(", ")
                    append(newsItem.date)
                    append(", ")
                    append(newsItem.time)
                },
                modifier = Modifier.padding(top = 6.dp), fontSize = 10.sp
            )

            SubcomposeAsyncImage(
                model = newsItem.imageUrl,
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 6.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}
