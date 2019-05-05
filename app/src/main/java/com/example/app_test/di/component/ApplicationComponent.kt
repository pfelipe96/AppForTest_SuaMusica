package com.example.app_test.di.component

import com.example.app_test.MainApplication
import com.example.app_test.di.module.NetworkModule
import com.example.app_test.di.module.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilsModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: MainApplication)
}