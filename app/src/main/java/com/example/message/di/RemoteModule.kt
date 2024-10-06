package com.example.message.di

import android.content.Context
import com.example.message.source.sources.remote.ApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttp(@ApplicationContext context: Context) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.SECONDS)
            .readTimeout(10000, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient) : ApiInterface {
        val gson = GsonBuilder().setLenient().create()

        val retrofit  = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("")
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}