package net.mouazkaadan.inshort.data.local

import androidx.compose.runtime.mutableStateListOf
import net.mouazkaadan.inshort.data.model.CategoryModel

/**
 * @author muaz
 * Created on 9/8/2022.
 */
object Categories {
    val categoriesList = mutableStateListOf(
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
        CategoryModel("Automobile", "automobile")
    )
}
