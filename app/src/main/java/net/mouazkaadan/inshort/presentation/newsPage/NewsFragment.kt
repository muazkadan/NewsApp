package net.mouazkaadan.inshort.presentation.newsPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.data.model.NewsItem
import net.mouazkaadan.inshort.databinding.NewsFragmentBinding
import net.mouazkaadan.inshort.presentation.newsPage.model.NewsController
import net.mouazkaadan.inshort.presentation.newsPage.model.OnNewsClickListener
import net.mouazkaadan.inshort.utils.extensions.asUri
import net.mouazkaadan.inshort.utils.extensions.showToast
import net.mouazkaadan.inshort.utils.viewBinding

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val binding by viewBinding(NewsFragmentBinding::bind)
    private val args: NewsFragmentArgs by navArgs()
    private val viewModel by viewModels<NewsViewModel>()

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

        binding.newsEpoxyRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val controller = NewsController(object : OnNewsClickListener<NewsItem> {
            override fun onShareClick(item: NewsItem?) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, item!!.readMoreUrl)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            override fun onItemClick(item: NewsItem?) {
                val browserIntent = Intent(Intent.ACTION_VIEW, item!!.readMoreUrl.asUri())
                requireActivity().startActivity(browserIntent, null)
            }
        })

        binding.newsEpoxyRv.setController(controller)

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                with(uiState) {
                    if (newsItems.isNotEmpty()) {
                        controller.list = uiState.newsItems
                    }
                    if (!errorMessage.isNullOrBlank()) {
                        requireContext().showToast(message = errorMessage)
                    }
                    binding.progressBar.visibility = if (isLoading) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
    }
}
