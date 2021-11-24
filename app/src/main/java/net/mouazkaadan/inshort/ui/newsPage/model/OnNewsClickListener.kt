package net.mouazkaadan.inshort.ui.newsPage.model

interface OnNewsClickListener<T> {
    fun onShareClick(item: T?)
    fun onItemClick(item: T?)
}
