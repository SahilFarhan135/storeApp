package com.ys.storeapp.injection.component


import android.content.Context
import com.ys.storeapp.injection.module.RepositoryModule
import com.ys.storeapp.injection.module.StorageModule
import com.ys.storeapp.injection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class,
        ViewModelModule::class, AppSubComponents::class,
        StorageModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory

}