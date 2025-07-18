package org.example.project

import android.app.Application
import com.example.Database
import data.DataBaseDriverFactory
import di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainApplication)
            androidLogger()
            //modules
            modules(AppModule(Database.invoke(DataBaseDriverFactory(this@MainApplication).createDriver())))
        }
    }
}