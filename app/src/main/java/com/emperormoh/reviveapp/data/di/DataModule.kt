package com.emperormoh.reviveapp.data.di

import android.content.Context
import com.emperormoh.reviveapp.data.local.AppDataStore
import com.emperormoh.reviveapp.data.local.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideAppDataStore( @ApplicationContext context: Context): AppDataStore {
        return AppDataStore(context)
    }

    @Singleton
    @Provides
    fun provideTokenManager(appDataStore: AppDataStore): TokenManager {
        return TokenManager(appDataStore)
    }

}