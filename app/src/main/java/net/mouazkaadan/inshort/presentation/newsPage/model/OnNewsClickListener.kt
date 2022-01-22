package net.mouazkaadan.inshort.presentation.newsPage.model

interface OnNewsClickListener<T> {
    fun onShareClick(item: T?)
    fun onItemClick(item: T?)
}
