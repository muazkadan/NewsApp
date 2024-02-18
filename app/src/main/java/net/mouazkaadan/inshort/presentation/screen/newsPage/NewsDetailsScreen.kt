package net.mouazkaadan.inshort.presentation.screen.newsPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import net.mouazkaadan.inshort.presentation.component.NewsCard
import net.mouazkaadan.inshort.presentation.component.NewsTopAppBar
import net.mouazkaadan.inshort.utils.extensions.showToast

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(viewModel: NewsViewModel, navController: NavController) {
    val context = LocalContext.current
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val listState = rememberLazyListState()

    val scrollUpState by viewModel.scrollUp.observeAsState()
    val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    viewModel.updateScrollPosition(firstVisibleItemIndex)

    val categoryName = viewModel.uiState.categoryName.orEmpty()

    Scaffold(topBar = {
        NewsTopAppBar(
            title = categoryName,
            scrollBehavior = scrollBehavior,
            scrollUpState = scrollUpState == true,
            onBack = {
                navController.popBackStack()
            }
        )
    }) {
        if (viewModel.uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        viewModel.uiState.errorMessage?.let { errorMessage ->
            context.showToast(errorMessage)
        }
        LazyColumn(
            modifier = Modifier
                .padding(it),
            state = listState
        ) {
            items(viewModel.uiState.newsItems) { news ->
                NewsCard(newsItem = news)
            }
        }
    }
}
