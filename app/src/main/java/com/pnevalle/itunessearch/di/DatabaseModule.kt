package com.pnevalle.itunessearch.di

import android.content.Context
import androidx.room.Room
import com.pnevalle.itunessearch.data.database.SearchResultDao
import com.pnevalle.itunessearch.data.database.SearchResultDaoService
import com.pnevalle.itunessearch.data.database.SearchResultDaoServiceImpl
import com.pnevalle.itunessearch.data.database.SearchResultDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module class to provide room database dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    /**
     * Provide the [SearchResultDatabase]
     *
     * @param context the application context
     * @return the [SearchResultDatabase]
     */
    @Provides
    @Singleton
    fun provideSearchResultDatabase(@ApplicationContext context: Context): SearchResultDatabase {
        return Room.databaseBuilder(context,
            SearchResultDatabase::class.java,
            SearchResultDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provide the [SearchResultDao]
     *
     * @param searchResultDatabase the search result room database
     * @return the [SearchResultDao]
     */
    @Provides
    @Singleton
    fun provideSearchResultDao(searchResultDatabase: SearchResultDatabase): SearchResultDao {
        return searchResultDatabase.getSearchResultDao()
    }

    /**
     * Provide the [SearchResultDaoService] dependency
     *
     * @param searchResultDaoServiceImpl the implementation class for [SearchResultDaoService]
     * @return the [SearchResultDaoService]
     */
    @Provides
    @Singleton
    fun provideSearchResultDaoService(searchResultDaoServiceImpl: SearchResultDaoServiceImpl): SearchResultDaoService {
        return searchResultDaoServiceImpl
    }
}