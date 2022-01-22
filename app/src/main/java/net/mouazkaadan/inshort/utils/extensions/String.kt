package net.mouazkaadan.inshort.utils.extensions

import android.net.Uri

fun String?.asUri(): Uri? {
    return try {
        Uri.parse(this)
    } catch (e: Exception) {
        null
    }
}
