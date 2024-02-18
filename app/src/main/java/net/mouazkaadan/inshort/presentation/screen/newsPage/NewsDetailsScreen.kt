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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.mouazkaadan.inshort.presentation.component.NewsCard
import net.mouazkaadan.inshort.presentation.component.NewsTopAppBar
import net.mouazkaadan.inshort.utils.ObserveAsEvents
import net.mouazkaadan.inshort.utils.extensions.showToast

/**
 * @author muaz
 * Created on 9/8/2022.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsContent(
    navController: NavController,
    state: NewsScreenState,
    onEvent: (NewsDetailsScreenEvent) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val listState = rememberLazyListState()
    val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    LaunchedEffect(key1 = firstVisibleItemIndex) {
        onEvent(NewsDetailsScreenEvent.UpdateScrollPosition(firstVisibleItemIndex))
    }

    Scaffold(topBar = {
        NewsTopAppBar(
            title = state.categoryName.orEmpty(),
            scrollBehavior = scrollBehavior,
            scrollUpState = state.isScrollUp,
            onBack = {
                navController.popBackStack()
            }
        )
    }) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(it),
            state = listState
        ) {
            items(state.newsItems) { news ->
                NewsCard(newsItem = news)
            }
        }
    }
}

@Composable
fun NewsDetailsScreen(viewModel: NewsViewModel, navController: NavController) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { uiEvent ->
        when (uiEvent) {
            is NewsViewModel.UIEvent.ShowError -> {
                context.showToast(uiEvent.message)
            }
        }
    }

    NewsDetailsContent(navController = navController, state = state, onEvent = viewModel::onEvent)
}

@Preview
@Composable
fun NewsDetailsScreenPreview() {
    NewsDetailsContent(navController = rememberNavController(), state = NewsScreenState(), onEvent = {})
}
