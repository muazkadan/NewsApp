package net.mouazkaadan.inshort.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.mouazkaadan.inshort.R
import net.mouazkaadan.inshort.databinding.CategoriesFragmentBinding
import net.mouazkaadan.inshort.presentation.categories.model.CategoryAdapter
import net.mouazkaadan.inshort.presentation.categories.model.CategoryModel
import net.mouazkaadan.inshort.presentation.categories.model.OnCategoryClickListener
import net.mouazkaadan.inshort.utils.viewBinding

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val binding by viewBinding(CategoriesFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesRv.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.categoriesRv.adapter = CategoryAdapter(
            categoriesList,
            object : OnCategoryClickListener<CategoryModel> {
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

    private val categoriesList: List<CategoryModel> = listOf(
        CategoryModel("All", "all"),
        CategoryModel("National", "national"),
        CategoryModel("Business", "business"),
        CategoryModel("Sports", "sports"),
        CategoryModel("World", "world"),
        CategoryModel("Politics", "politics"),
        CategoryModel("Technology", "technology"),
        CategoryModel("Startup", "startup"),
        CategoryModel("Entertainment", "entertainment"),
        CategoryModel("Miscellaneous", "miscellaneous"),
        CategoryModel("Hatke", "hatke"),
        CategoryModel("Science", "science"),
        CategoryModel("Automobile", "automobile"),
    )
}
