package com.pnevalle.itunessearch.di

import com.pnevalle.itunessearch.api.SearchService
import com.pnevalle.itunessearch.common.BASE_URL
import com.pnevalle.itunessearch.data.SearchDataImpl
import com.pnevalle.itunessearch.data.SearchDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Hilt module class to provide network dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    /**
     * Provide the retrofit dependency
     *
     * @return the [Retrofit] instance
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provide the search API service dependency
     *
     * @param retrofit the [Retrofit] instance to use in creating the API service
     * @return the [SearchService]
     */
    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    /**
     * Provide the [SearchDataSource] dependency
     *
     * @param searchDataImpl the implementation class for [SearchDataSource]
     * @return the [SearchDataSource]
     */
    @Singleton
    @Provides
    fun provideSearchDataSource(searchDataImpl: SearchDataImpl): SearchDataSource {
        return searchDataImpl
    }
}