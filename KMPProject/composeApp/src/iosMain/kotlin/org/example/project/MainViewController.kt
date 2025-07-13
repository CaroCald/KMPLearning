package org.example.project

import App
import androidx.compose.ui.window.ComposeUIViewController
import com.example.Database
import data.DataBaseDriverFactory
import di.AppModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }
fun initKoin(){
    startKoin {
        modules(AppModule(Database.invoke(DataBaseDriverFactory().createDriver())))
    }.koin
}