package com.ys.storeapp.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityMainBinding
import com.ys.storeapp.databinding.DilaogMenuBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.account.AccountActivity
import com.ys.storeapp.ui.add_store.AddStoreActivity
import com.ys.storeapp.ui.addproduct.AddProductActivity
import com.ys.storeapp.ui.addproduct.AddProductViewModel
import com.ys.storeapp.ui.home.adpaters.CategoryAdapter
import com.ys.storeapp.ui.home.adpaters.ProductApadter
import com.ys.storeapp.ui.home.model.CategoryModel
import com.ys.storeapp.ui.home.model.ProductItem


class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>(),ProductApadter.OnProductMenuClick, NavigationView.OnNavigationItemSelectedListener {


    private lateinit var categoryList: ArrayList<CategoryModel>
    private lateinit var allItemList: ArrayList<ProductItem>
    val END_SCALE = 0.7f

    override fun layoutId(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    override fun addObservers() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
        clickListener()
    }


    private fun initui() {
        categoryList = ArrayList()
        allItemList = ArrayList()
        intializeRvShop()
        intializeRvAllItems()
        binding.navView.setNavigationItemSelectedListener(this)
    }



    private fun clickListener() {
        binding.activityMainDr.btDrawer.setOnClickListener {
            if(binding.drawerLayout.isDrawerVisible(GravityCompat.START)){
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
            animateNavigationDrawer()
        }

        binding.activityMainDr.btFilter.setOnClickListener {
            showToast("Filter will be added soon")
        }
        binding.activityMainDr.imgProfile.setOnClickListener {
            navigator.startActivity(AccountActivity::class.java, true)
        }
    }

    private fun intializeRvShop() {
        categoryList.clear()
        categoryList.add(CategoryModel(R.drawable.ic_shop, "Sahil\n Footwear"))
        categoryList.add(CategoryModel(R.drawable.ic_shop, "Tea \nStory"))
        categoryList.add(CategoryModel(R.drawable.ic_shop, "Malik\n Store"))
        categoryList.add(CategoryModel(R.drawable.ic_shop, "Malik\n Store"))
        categoryList.add(CategoryModel(R.drawable.ic_shop, "Mk\n Clothing"))
        val adapter =
            CategoryAdapter(categoryList, this)
        binding.activityMainDr.rvMyStore.setAdapter(adapter)
        binding.activityMainDr.rvMyStore.setLayoutManager(GridLayoutManager(this, 3))
    }


    private fun intializeRvAllItems() {
        allItemList.clear()
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        val adapter1 = ProductApadter()
        adapter1.ProductApadterCallBack(this)
        binding.activityMainDr.rvMyProduct.adapter=adapter1
        binding.activityMainDr.rvMyProduct.setLayoutManager(GridLayoutManager(this, 2))
        (binding.activityMainDr.rvMyProduct.adapter as ProductApadter).submitList(allItemList)
    }
    private fun animateNavigationDrawer() {
        binding.drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val diffScaledOffset: Float = slideOffset * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
            }
        })
    }

    override fun onMenuClick(productItem: ProductItem, position: Int) {
        showToast(productItem.toString() + "position  $position")
        openMenuDialog(productItem, position)
    }
    fun openMenuDialog(productItem: ProductItem, position: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        val binding: DilaogMenuBinding = DilaogMenuBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding.getRoot())
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        dialog.show()
        var click=true
        with(binding){
            clRename.setOnClickListener{
                if(click){
                    clRenameLayout.visibility=View.VISIBLE
                    clChangePriceLayout.visibility=View.GONE
                    click=false
                }else{
                    clRenameLayout.visibility=View.GONE
                    clChangePriceLayout.visibility=View.GONE
                    click=true
                }

            }
            tvBtRename.setOnClickListener {
                if(etRename.text.isNullOrBlank()){
                    showToast("Name filed cannot be empty")
                }else{
                    showToast("Your Product name has been changed to ${etRename.text.toString().trim()}")
                }
            }
            tvBtPriceChange.setOnClickListener {
                if(etChangePrice.text.isNullOrBlank()){
                    showToast("Price filed cannot be empty")
                }else{
                    showToast("Your price has been updated to ${etChangePrice.text.toString().trim()}")
                }
            }
            clPrice.setOnClickListener {

                if(click){
                    clRenameLayout.visibility=View.GONE
                    clChangePriceLayout.visibility=View.VISIBLE
                    click=false
                }else{
                    clRenameLayout.visibility=View.GONE
                    clChangePriceLayout.visibility=View.GONE
                    click=true
                }
            }
            clDlt.setOnClickListener {
                showToast("Your product is deleted")
            }
            clOutOfStock.setOnClickListener {
                showToast("Your Product has been updated to out of stock")
            }
        }

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_product -> {
                var intent=Intent(this,AddProductActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Add Product", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_store -> {
                var intent=Intent(this,AddStoreActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Add Store Store", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_change_language -> {
                Toast.makeText(this, "Change Language ", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_change_password -> {
                Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_privacy_policy -> {
                Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}