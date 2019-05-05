package com.example.app_test.di.component

import com.example.app_test.MainApplication
import com.example.app_test.di.module.ApplicationModule
import com.example.app_test.di.module.NetworkModule
import com.example.app_test.mvp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: MainApplication)
    fun inject(mainActivity: MainActivity)
}