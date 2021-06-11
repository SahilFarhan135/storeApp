package com.ys.storeapp.ui.splash

import android.os.Bundle
import android.os.Handler
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivitySplashBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.login.LoginActivity

class SplashActitvity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun layoutId(): Int = R.layout.activity_splash
    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    override fun addObservers() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {
        Handler().postDelayed({
            navigator.startActivity(LoginActivity::class.java, true)
            finish()
        }, 2000)
    }


}