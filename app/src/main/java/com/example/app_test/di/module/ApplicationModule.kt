package com.example.app_test.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.app_test.mvp.room.FavoriteDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: Application) {
    var applicationInst = application

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return applicationInst
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesFavoriteDatabase() : FavoriteDatabase{
        return Room.databaseBuilder(applicationInst, FavoriteDatabase::class.java, "favorite-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}