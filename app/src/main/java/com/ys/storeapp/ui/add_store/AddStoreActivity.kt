package com.ys.storeapp.ui.add_store

import android.os.Bundle
import android.widget.ArrayAdapter
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityAddStoreBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.HomeActivity

class AddStoreActivity: BaseActivity<ActivityAddStoreBinding, AddStoreViewModel>() {

    override fun layoutId(): Int = R.layout.activity_add_store
    override fun getViewModelClass(): Class<AddStoreViewModel> = AddStoreViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {
        val categories = arrayOf("Footwear", "Grocery", "Bakery", "Clothing", "Vegetables", "FastFood")

        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, categories)
        binding.actvCategory.setAdapter(adapter)
        binding.btAddShop.setOnClickListener{
            navigator.startActivity(HomeActivity::class.java, true)
        }
    }
}