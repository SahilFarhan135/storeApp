package com.ys.storeapp.ui.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityLoginBinding
import com.ys.storeapp.databinding.ActivitySplashBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.HomeActivity
import com.ys.storeapp.ui.signup.SignupActivity
import com.ys.storeapp.util.RuntimePermissionHelper

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun layoutId(): Int = R.layout.activity_login
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    companion object {
        val permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private val permissionHelper: RuntimePermissionHelper by lazy {
        RuntimePermissionHelper.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED)
            showToast("Required Permission not granted")
        super.onBackPressed()
    }

    private fun initui() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionHelper.isPermissionsAvailable(permissions).not()) {
                permissionHelper.requestPermissions(permissions)
            }
        }

        binding.tvSignUp.setOnClickListener {
            navigator.startActivity(SignupActivity::class.java,true)
        }

        binding.btLogin.setOnClickListener{
            navigator.startActivity(HomeActivity::class.java,true)
            finish()
        }
    }
}