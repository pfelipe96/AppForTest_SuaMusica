package com.example.app_test

import android.app.Application
import com.example.app_test.di.component.ApplicationComponent
import com.example.app_test.di.component.DaggerApplicationComponent
import com.example.app_test.di.module.NetworkModule
import com.example.app_test.di.module.UtilsModule

class MainApplication: Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .utilsModule(UtilsModule(this))
            .networkModule(NetworkModule())
            .build()

        applicationComponent.inject(this)
    }
}