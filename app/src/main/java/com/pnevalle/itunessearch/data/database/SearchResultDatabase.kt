package com.pnevalle.itunessearch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pnevalle.itunessearch.data.SearchResult

/**
 * The [SearchResult] Room database
 */
@Database(entities = [SearchResult::class], version = 1, exportSchema = false)
abstract class SearchResultDatabase: RoomDatabase() {
    abstract fun getSearchResultDao(): SearchResultDao

    companion object {
        const val DATABASE_NAME = "search_result_db"
    }
}