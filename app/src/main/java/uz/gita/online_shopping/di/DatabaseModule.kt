package uz.gita.online_shopping.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.online_shopping.data.prefs.MySharedPref
import uz.gita.online_shopping.data.remote.MaxWayApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Created by Jamshid Isoqov an 10/9/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val BASE_URL = ""

    private const val CONNECTION_TIME_OUT = 5000L

    @[Provides Singleton]
    fun provideClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @[Provides Singleton]
    fun provideMaxWayApi(retrofit: Retrofit): MaxWayApi =
        retrofit.create(MaxWayApi::class.java)

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref =
        MySharedPref(context, sharedPreferences)

}