package net.mouazkaadan.inshort.ui.newsPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.base.OnItemClickListener
import net.mouazkaadan.inshort.databinding.NewsFragmentBinding
import net.mouazkaadan.inshort.ui.newsPage.model.Data
import net.mouazkaadan.inshort.ui.newsPage.model.NewsAdapter
import net.mouazkaadan.inshort.utils.showToast

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var viewBinding: NewsFragmentBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, null, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewBinding.viewModel = viewModel

        navArgs<NewsFragmentArgs>().value.apply {
            viewModel.getNews(category)
        }

        viewBinding.newsRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = NewsAdapter(object : OnItemClickListener<Data> {
            override fun onItemClick(item: Data?) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, item!!.readMoreUrl)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        })

        viewBinding.newsRv.adapter = adapter

        viewModel.newsResponse.observe(viewLifecycleOwner, {
            viewBinding.progressBar.visibility = View.GONE
            adapter.setNewsList(it.data)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            requireContext().showToast(it)
            findNavController().navigateUp()
        })
    }
}
