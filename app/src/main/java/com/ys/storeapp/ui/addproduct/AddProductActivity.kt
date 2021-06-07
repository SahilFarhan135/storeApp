package com.ys.storeapp.ui.addproduct

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityAddProductBinding
import com.ys.storeapp.databinding.ActivityAddStoreBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.HomeActivity

class AddProductActivity: BaseActivity<ActivityAddProductBinding, AddProductViewModel>() {

    override fun layoutId(): Int = R.layout.activity_add_product
    override fun getViewModelClass(): Class<AddProductViewModel> = AddProductViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {
      binding.imgUploadPic.setOnClickListener {
          binding.clUploadedPic.visibility=View.VISIBLE
      }
    }
}