package net.mouazkaadan.inshort.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.mouazkaadan.inshort.data.local.entity.NewsItemEntity

@Dao
interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsItems(items: List<NewsItemEntity>)

    @Delete
    suspend fun deleteNewsItems(items: List<NewsItemEntity>)

    @Query("SELECT * FROM NewsItemEntity WHERE category IN (:newsCategory)")
    suspend fun getNewsItems(newsCategory: String): List<NewsItemEntity>
}
