package com.ys.storeapp.injection.component

import com.ys.storeapp.injection.scope.ActivityScope
import com.ys.storeapp.ui.account.AccountActivity
import com.ys.storeapp.ui.add_store.AddStoreActivity
import com.ys.storeapp.ui.addproduct.AddProductActivity
import com.ys.storeapp.ui.home.HomeActivity
import com.ys.storeapp.ui.login.LoginActivity
import com.ys.storeapp.ui.product.ProductsActivity
import com.ys.storeapp.ui.signup.SignupActivity
import com.ys.storeapp.ui.splash.SplashActitvity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: SplashActitvity)
    fun inject(activity: LoginActivity)
    fun inject(activity: SignupActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: AccountActivity)
    fun inject(activity: ProductsActivity)
    fun inject(activity: AddStoreActivity)
    fun inject(activity: AddProductActivity)
}