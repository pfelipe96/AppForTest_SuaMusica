package com.example.app_test.di.module

import com.example.app_test.network.SeatGeekNetwork
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule{

    @Provides
    @Named("SeatGeekApi")
    fun provideSeatGeekAPI(gson: Gson, @Named("CachedOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.seatgeek.com/2/")
            .build()
    }

    @Provides
    fun provideSeatGeekService(@Named("SeatGeekApi") seatGeekApi: Retrofit): SeatGeekNetwork{
        return seatGeekApi.create(SeatGeekNetwork::class.java)
    }
}