package com.ys.storeapp.ui.addproduct

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityAddProductBinding
import com.ys.storeapp.databinding.ActivityAddStoreBinding
import com.ys.storeapp.databinding.BottomSheetPictureBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.add_store.AddStoreActivity
import com.ys.storeapp.ui.home.HomeActivity

class AddProductActivity: BaseActivity<ActivityAddProductBinding, AddProductViewModel>() {

    override fun layoutId(): Int = R.layout.activity_add_product
    override fun getViewModelClass(): Class<AddProductViewModel> = AddProductViewModel::class.java
    override fun addObservers() {}
    lateinit var bindingBottomSheet: BottomSheetPictureBinding
    lateinit var bottomSheet:BottomSheetDialog
    var firstPic=false
    var secondPic=false
    var thirdPic=false


    companion object {
        private const val REQUEST_OPEN_FILES = 1888
        private const val REQUEST_OPEN_GALLERY = 1889
    }

    override fun injectActivity(appComponent: AppComponent) {
      appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
        clickListner()
    }

    private fun clickListner() {
        binding.imgUploadPic.setOnClickListener {
           openBottomSheetDialog()
        }
        bindingBottomSheet.clOpenGallery.setOnClickListener {
            openGallery(REQUEST_OPEN_GALLERY)
        }

        bindingBottomSheet.clOpenFiles.setOnClickListener {
            openFile(REQUEST_OPEN_FILES)
        }
        binding.imgBtBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initui() {
        bottomSheet = BottomSheetDialog(this)
        bindingBottomSheet = BottomSheetPictureBinding.inflate(LayoutInflater.from(this))
        val units = arrayOf("Kg", "Piece", "Gram", "Liter", "Dozen", "Meter","Pound","Ounce")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, units)
        binding.actvUnit.setAdapter(adapter)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            when(requestCode){
                REQUEST_OPEN_FILES ->{
                    bottomSheet.dismiss()
                    binding.clUploadedPic.visibility=View.VISIBLE
                    if(binding.imgPic1.drawable==null){
                        binding.imgPic1.setImageURI(data!!.data)
                    }else if(binding.imgPic2.drawable==null){
                        binding.imgPic2.setImageURI(data!!.data)
                    }else if(binding.imgPic3.drawable==null){
                        binding.imgPic3.setImageURI(data!!.data)
                    }
                }
                REQUEST_OPEN_GALLERY -> {
                    bottomSheet.dismiss()
                    binding.clUploadedPic.visibility=View.VISIBLE
                    if(binding.imgPic1.drawable==null){
                        binding.imgPic1.setImageURI(data!!.data)
                    }else if(binding.imgPic2.drawable==null){
                        binding.imgPic2.setImageURI(data!!.data)
                    }else if(binding.imgPic3.drawable==null){
                        binding.imgPic3.setImageURI(data!!.data)
                    }else{
                        showToast("Max 3 Images are required")
                    }
                }
            }
        }
    }
    fun openBottomSheetDialog(){
        bottomSheet.setContentView(bindingBottomSheet.root)
        bottomSheet.show()
    }

    private fun openFile(request: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(intent, request)
    }

    fun openGallery(request: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, request)
    }
}