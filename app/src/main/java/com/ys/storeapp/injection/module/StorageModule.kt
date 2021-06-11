package com.ys.storeapp.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.ys.storeapp.util.PrefsUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            PrefsUtil.SHARED_PREFERENCE_ID, Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providePrefUtils(sharedPreferences: SharedPreferences): PrefsUtil {
        return PrefsUtil(sharedPreferences)
    }


}