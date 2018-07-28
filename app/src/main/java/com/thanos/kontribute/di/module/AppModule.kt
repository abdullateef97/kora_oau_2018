package com.thanos.kontribute.di.module

import android.content.Context
import android.content.SharedPreferences
import com.thanos.kontribute.App
import com.atlascc.kontribute.data.remote.ApiService
import com.thanos.kontribute.helper.BASE_URL
import com.thanos.kontribute.helper.SharedPrefHelper
import com.atlascc.kontribute.util.ImageUtil
import com.atlascc.kontribute.util.NetworkUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thanos.kontribute.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(var app: App) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpClient.Builder =
            OkHttpClient.Builder()
                    .connectTimeout(72, TimeUnit.SECONDS)
                    .writeTimeout(72, TimeUnit.SECONDS)
                    .readTimeout(72, TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun provideGson(): Gson =
            GsonBuilder()
                    .setLenient()
                    .create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else HttpLoggingInterceptor.Level.NONE
            }

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            builder.addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient, gson: Gson): Retrofit.Builder =
            Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    @Singleton
    fun provideApiService(builder: Retrofit.Builder): ApiService =
            builder.baseUrl(BASE_URL)
                    .build()
                    .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefHelper(sharedPreferences: SharedPreferences): SharedPrefHelper {
        return SharedPrefHelper(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideImageUtil(context: Context): ImageUtil = ImageUtil(context)

    @Provides
    @Singleton
    fun provideNetworkUtil(context: Context): NetworkUtil = NetworkUtil(context)


}