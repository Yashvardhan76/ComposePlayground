package com.justlime.composeplayground.data.local.di

import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.domain.repository.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQuotesApi(): QuotesApi {
        return Retrofit.Builder()
            .baseUrl(QuotesApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }

    @Provides
    fun provideQuotesRepository(api: QuotesApi): QuotesRepository {
        return QuotesRepository(api)
    }
}