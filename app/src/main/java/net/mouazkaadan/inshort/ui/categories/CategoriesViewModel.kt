package net.mouazkaadan.inshort.ui.categories

import androidx.lifecycle.ViewModel
import net.mouazkaadan.inshort.ui.categories.model.CategoryModel

class CategoriesViewModel : ViewModel() {

    val categoriesList: List<CategoryModel> = listOf(
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
