package net.mouazkaadan.inshort.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.mouazkaadan.inshort.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesTopAppBar(modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(
        modifier = modifier.height(48.dp),
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
