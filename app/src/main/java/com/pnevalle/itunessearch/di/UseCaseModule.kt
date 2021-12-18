package com.pnevalle.itunessearch.di

import com.pnevalle.itunessearch.data.SearchDataImpl
import com.pnevalle.itunessearch.domain.SearchAppleStoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.pnevalle.itunessearch.domain.SearchInteractors

/**
 * Hilt module class to provide use case dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    /**
     * Provide the search use case dependency for [SearchInteractors]
     *
     * @param searchImpl the [SearchDataImpl] class
     */
    @Provides
    fun provideSearchUseCase(searchImpl: SearchDataImpl): SearchAppleStoreUseCase {
        return SearchAppleStoreUseCase(searchImpl)
    }
}