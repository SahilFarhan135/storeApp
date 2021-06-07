package com.ys.storeapp

import android.app.Application
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.injection.component.DaggerAppComponent

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}
//TODO: replace google-services.json with original