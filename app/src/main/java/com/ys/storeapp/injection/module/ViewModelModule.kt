package com.ys.storeapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ys.storeapp.injection.scope.ViewModelScope
import com.ys.storeapp.ui.account.AccountViewModel
import com.ys.storeapp.ui.add_store.AddStoreViewModel
import com.ys.storeapp.ui.addproduct.AddProductViewModel
import com.ys.storeapp.ui.delivery_current.MyOrderViewModel
import com.ys.storeapp.ui.home.HomeViewModel
import com.ys.storeapp.ui.login.LoginViewModel
import com.ys.storeapp.ui.product.ProductViewModel
import com.ys.storeapp.ui.signup.SignupViewModel
import com.ys.storeapp.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelScope(SplashViewModel::class)
    abstract fun bindMainViewModel(viewModel: SplashViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelScope(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelScope(SignupViewModel::class)
    abstract fun bindSignupViewModel(viewModel: SignupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelScope(ProductViewModel::class)
    abstract fun bindProductViewModel(viewModel: ProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(AddStoreViewModel::class)
    abstract fun bindAddStoreViewModel(viewModel: AddStoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(AddProductViewModel::class)
    abstract fun bindAddProductViewModel(viewModel: AddProductViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelScope(MyOrderViewModel::class)
    abstract fun bindCurrentOrderViewModel(viewModel: MyOrderViewModel): ViewModel


}

