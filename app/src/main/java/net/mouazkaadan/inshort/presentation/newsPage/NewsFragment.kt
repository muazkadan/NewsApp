package net.mouazkaadan.inshort.presentation.newsPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.databinding.NewsFragmentBinding
import net.mouazkaadan.inshort.presentation.newsPage.model.NewsController
import net.mouazkaadan.inshort.utils.extensions.asUri
import net.mouazkaadan.inshort.utils.extensions.showToast
import net.mouazkaadan.inshort.utils.viewBinding

@AndroidEntryPoint
class NewsFragment : Fragment(), NewsController.OnNewsClickListener {

    private val binding by viewBinding(NewsFragmentBinding::bind)
    private val args: NewsFragmentArgs by navArgs()
    private val viewModel by viewModels<NewsViewModel>()

    private val controller by lazy {
        NewsController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews(args.category)
        initViews()
        observe()
    }

    private fun initViews() = with(binding) {
        newsEpoxyRv.setController(controller)
    }

    private fun observe() = with(viewModel) {
        lifecycleScope.launch {
            uiState.collect { uiState ->
                with(uiState) {
                    if (newsItems.isNotEmpty()) {
                        controller.list = uiState.newsItems
                    }
                    if (!errorMessage.isNullOrBlank()) {
                        requireContext().showToast(message = errorMessage)
                    }
                    binding.progressBar.isVisible = isLoading
                }
            }
        }
    }

    override fun onShareClick(item: NewsItem) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, item.readMoreUrl)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onItemClick(item: NewsItem) {
        val browserIntent = Intent(Intent.ACTION_VIEW, item.readMoreUrl.asUri())
        requireActivity().startActivity(browserIntent, null)
    }
}
