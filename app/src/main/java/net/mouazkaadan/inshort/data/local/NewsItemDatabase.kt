package net.mouazkaadan.inshort.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.mouazkaadan.inshort.data.local.entity.NewsItemEntity

@Database(
    entities = [NewsItemEntity::class],
    version = 1
)
abstract class NewsItemDatabase : RoomDatabase() {

    abstract val dao: NewsItemDao
}
