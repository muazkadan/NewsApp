package net.mouazkaadan.inshort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import net.mouazkaadan.inshort.presentation.navigation.Navigation
import net.mouazkaadan.inshort.utils.theme.InShortTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InShortTheme {
                Navigation()
            }
        }
    }
}
