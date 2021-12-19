package com.pnevalle.itunessearch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pnevalle.itunessearch.data.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * The DAO for [SearchResult], defining the methods that access the database
 */
@Dao
interface SearchResultDao {

    @Query("SELECT * FROM search_result")
    fun getSearchResults(): Flow<List<SearchResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(searchResults: List<SearchResult>)
}