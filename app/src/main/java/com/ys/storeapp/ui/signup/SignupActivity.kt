package com.ys.storeapp.ui.signup

import android.os.Bundle
import android.view.View
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityLoginBinding
import com.ys.storeapp.databinding.ActivitySignupBinding
import com.ys.storeapp.databinding.ActivitySplashBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.add_store.AddStoreActivity
import com.ys.storeapp.ui.home.HomeActivity

class SignupActivity: BaseActivity<ActivitySignupBinding, SignupViewModel>() {

    override fun layoutId(): Int = R.layout.activity_signup
    override fun getViewModelClass(): Class<SignupViewModel> = SignupViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
        clickListner()
    }

    private fun clickListner() {
        binding.btSignup.setOnClickListener{
            navigator.startActivity(HomeActivity::class.java,true)
            finish()

        }
        binding.btGetOtp.setOnClickListener {
            binding.btSignup.visibility= View.VISIBLE
            binding.clBoxLayout.visibility=View.GONE
            binding.clBoxLayoutOtp.visibility=View.VISIBLE
        }

    }

    private fun initui() {
        with(binding){
            clBoxLayoutOtp.visibility=View.GONE
            clBoxLayout.visibility=View.VISIBLE
            btSignup.visibility=View.GONE
            btGetOtp.visibility=View.VISIBLE
        }
    }

}