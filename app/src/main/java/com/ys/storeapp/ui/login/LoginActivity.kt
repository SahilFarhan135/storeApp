package com.ys.storeapp.ui.login

import android.os.Bundle
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityLoginBinding
import com.ys.storeapp.databinding.ActivitySplashBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.HomeActivity
import com.ys.storeapp.ui.signup.SignupActivity

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun layoutId(): Int = R.layout.activity_login
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {
        binding.tvSignUp.setOnClickListener {
            navigator.startActivity(SignupActivity::class.java,true)
        }

        binding.btLogin.setOnClickListener{
            navigator.startActivity(HomeActivity::class.java,true)
        }
    }
}