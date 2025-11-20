package com.emperormoh.reviveapp.data.di

import com.emperormoh.reviveapp.data.local.AppDataStore
import com.emperormoh.reviveapp.data.local.TokenManager
import com.emperormoh.reviveapp.data.remote.ReviveApiService
import com.emperormoh.reviveapp.utils.AuthInterceptor
import com.emperormoh.reviveapp.utils.JsonUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)       // attach token
            .addInterceptor(loggingInterceptor)    // log requests
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideReviveRetrofit(@AuthenticatedClient okHttpClient: OkHttpClient): Retrofit =
//        Retrofit.Builder()
//            .baseUrl("https://api.example.com/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(JsonUtils.gson))
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideApi(@AuthenticatedClient okHttpClient: OkHttpClient): ReviveApiService =
//        provideReviveRetrofit(okHttpClient = okHttpClient).create(ReviveApiService::class.java)


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(JsonUtils.gson))
            .build()

    @Provides
    @Singleton
    fun provideReviveApi(retrofit: Retrofit): ReviveApiService =
        retrofit.create(ReviveApiService::class.java)
}