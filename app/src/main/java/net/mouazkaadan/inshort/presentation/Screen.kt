package net.mouazkaadan.inshort.presentation

/**
 * @author muaz
 * Created on 9/8/2022.
 */
sealed class Screen(val route: String) {
    object CategoriesScreen : Screen("categories_screen")
    object NewsDetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
