package com.ys.storeapp.ui.add_store

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityAddStoreBinding
import com.ys.storeapp.databinding.BottomSheetPictureBinding
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.ui.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddStoreActivity : BaseActivity<ActivityAddStoreBinding, AddStoreViewModel>() {

    override fun layoutId(): Int = R.layout.activity_add_store
    override fun getViewModelClass(): Class<AddStoreViewModel> = AddStoreViewModel::class.java
    override fun addObservers() {}


    lateinit var mCursor: Cursor
    lateinit var listOfFile: ArrayList<String?>
    lateinit var bindingBottomSheet: BottomSheetPictureBinding
    lateinit var bottomSheet: BottomSheetDialog

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
        //val mainLooper = Looper.getMainLooper()
        //  getImages(mainLooper);
    }


    private fun initui() {
        bottomSheet = BottomSheetDialog(this)
        bindingBottomSheet = BottomSheetPictureBinding.inflate(LayoutInflater.from(this))

        listOfFile = ArrayList()
        val categories = arrayOf("Footwear", "Grocery", "Bakery", "Clothing", "Vegetables", "FastFood")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, categories)
        binding.actvCategory.setAdapter(adapter)

    }

    private fun clickListner() {
        binding.btAddShop.setOnClickListener {
            navigator.startActivity(HomeActivity::class.java, true)
        }
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

    @SuppressLint("InlinedApi", "Recycle")
    private fun getImages(mainLooper: Looper) {
        GlobalScope.launch(Dispatchers.IO) {
            val uri: Uri
            val listOfAllImages = ArrayList<String?>()
            val cursor: Cursor?
            val column_index_data: Int
            val column_index_folder_name: Int
            var PathOfImage: String? = null
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            cursor = this@AddStoreActivity.contentResolver.query(uri, projection, null,
                    null, null)
            column_index_data = cursor!!.getColumnIndexOrThrow(MediaColumns.DATA)
            column_index_folder_name = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                PathOfImage = cursor.getString(column_index_folder_name)
                listOfAllImages.add(PathOfImage)
            }
            listOfFile = listOfAllImages

            Handler(mainLooper).post {
                showToast(listOfFile.toString())
            }

        }
    }

    fun openBottomSheetDialog() {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_OPEN_FILES -> {
                    bottomSheet.dismiss()
                    binding.imgBtAddPhoto.visibility = View.VISIBLE
                    binding.imgBtAddPhoto.setImageURI(data!!.data)
                }
                REQUEST_OPEN_GALLERY -> {
                    bottomSheet.dismiss()
                    binding.imgBtAddPhoto.visibility = View.VISIBLE
                    binding.imgBtAddPhoto.setImageURI(data!!.data)
                }
            }
        }
    }

}


