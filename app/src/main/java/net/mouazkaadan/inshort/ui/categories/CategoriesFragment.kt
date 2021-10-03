package net.mouazkaadan.inshort.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.base.OnItemClickListener
import net.mouazkaadan.inshort.databinding.CategoriesFragmentBinding
import net.mouazkaadan.inshort.ui.categories.model.CategoryAdapter
import net.mouazkaadan.inshort.ui.categories.model.CategoryModel

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var viewBinding: CategoriesFragmentBinding
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = DataBindingUtil.inflate(inflater, R.layout.categories_fragment, null, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewBinding.viewModel = viewModel

        viewBinding.categoriesRv.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewBinding.categoriesRv.adapter = CategoryAdapter(
            viewModel.categoriesList,
            object : OnItemClickListener<CategoryModel> {
                override fun onItemClick(item: CategoryModel?) {
                    findNavController().navigate(
                        CategoriesFragmentDirections.actionCategoriesFragmentToNewsFragment(
                            item!!.value
                        )
                    )
                }
            }
        )
    }
}
