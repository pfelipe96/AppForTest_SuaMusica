package com.example.app_test.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UtilsModule(val application: Application){

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

//    @Provides
//    @Singleton
//    fun providesSeatGeekDataBase(){
//        Room.databaseBuilder(application, )
//    }

}