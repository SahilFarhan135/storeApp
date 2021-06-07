package com.ys.storeapp.ui.product

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityProductBinding
import com.ys.storeapp.databinding.DilaogMenuBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.adpaters.ProductApadter
import com.ys.storeapp.ui.home.model.ProductItem

class ProductsActivity: BaseActivity<ActivityProductBinding, ProductViewModel>() ,ProductApadter.OnProductMenuClick{

    var storeName=""
    private lateinit var allItemList: ArrayList<ProductItem>

    override fun layoutId(): Int = R.layout.activity_product
    override fun getViewModelClass(): Class<ProductViewModel> = ProductViewModel::class.java
    override fun addObservers() {}

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initui()
        clickListner()
    }

    private fun clickListner() {
        binding.btBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getData() {
      storeName=intent.getStringExtra("name").toString()
    }

    private fun initui() {
        allItemList = ArrayList()
        binding.tvStoreName.text=storeName
        binding.tvStoreLocation.text="Jamshedpur"
        intializeRv()
    }

    private fun intializeRv() {
        allItemList.clear()
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        allItemList.add(ProductItem("Nike Super Light", "500", R.drawable.demo_shoe))
        val adapter1 = ProductApadter()
        binding.rvMyProduct.adapter=adapter1
        adapter1.ProductApadterCallBack(this)
        binding.rvMyProduct.setLayoutManager(GridLayoutManager(this, 2))
        (binding.rvMyProduct.adapter as ProductApadter).submitList(allItemList)    }

    override fun onMenuClick(productItem: ProductItem, position: Int) {
        showToast(productItem.toString()+"position  $position")
        openMenuDialog(productItem,position)
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
                    clRenameLayout.visibility= View.VISIBLE
                    clChangePriceLayout.visibility= View.GONE
                    click=false
                }else{
                    clRenameLayout.visibility= View.GONE
                    clChangePriceLayout.visibility= View.GONE
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
                    clRenameLayout.visibility= View.GONE
                    clChangePriceLayout.visibility= View.VISIBLE
                    click=false
                }else{
                    clRenameLayout.visibility= View.GONE
                    clChangePriceLayout.visibility= View.GONE
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

}