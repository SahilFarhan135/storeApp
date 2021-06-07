package com.ys.storeapp.ui.account

import android.os.Bundle
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityLoginBinding
import com.ys.storeapp.databinding.ActivitySplashBinding
import com.ys.storeapp.injection.component.AppComponent

class AccountActivity: BaseActivity<ActivityLoginBinding, AccountViewModel>() {

    override fun layoutId(): Int = R.layout.activity_login
    override fun getViewModelClass(): Class<AccountViewModel> = AccountViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {

    }
}