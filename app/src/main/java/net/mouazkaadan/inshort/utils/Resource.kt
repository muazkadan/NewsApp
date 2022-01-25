package net.mouazkaadan.inshort.utils

sealed class Resource<out T> {
    class Success<T>(val data: T?) : Resource<T>()
    class Error(val message: String?) : Resource<Nothing>()
    class Loading<T>(val data: T?) : Resource<T>()
}
