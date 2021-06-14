package com.ys.storeapp.ui.account

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityAccountBinding
import com.ys.storeapp.injection.component.AppComponent
import java.io.File
import java.io.FileOutputStream


class AccountActivity : BaseActivity<ActivityAccountBinding, AccountViewModel>() {

    override fun layoutId(): Int = R.layout.activity_account
    override fun getViewModelClass(): Class<AccountViewModel> = AccountViewModel::class.java
    override fun addObservers() {}

    lateinit var bitmap: Bitmap


    companion object {
        private const val REQUEST_OPEN_FILES = 1888
        private const val REQUEST_OPEN_GALLERY = 1889
        private const val REQUEST_CODE_ASK_PERMISSIONS = 123
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
        binding.btBack.setOnClickListener {
            onBackPressed()
        }
        binding.imgChangeShopPic.setOnClickListener {
            openGallery(REQUEST_OPEN_GALLERY)
        }
        binding.tvShare.setOnClickListener {
            shareQrCode()
        }
        binding.tvDownload.setOnClickListener {
            downloadQrCode(bitmap)
        }
    }

    private fun initui() {
        val qrgEncoder = QRGEncoder("MK4945393", null, QRGContents.Type.TEXT, 2500)
        try {
            bitmap = qrgEncoder.bitmap
            binding.imgQrcode.setImageBitmap(bitmap)

        } catch (e: Exception) {

            Log.e("Tag", e.toString())
        }

    }


    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat
                .requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_ASK_PERMISSIONS
                )
        }
    }

    fun downloadQrCode(bitmap: Bitmap) {
        val root = Environment.getExternalStorageDirectory().toString()
        var myDir = File("$root/myStoreApp")
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        val fileName = "MyStoreQr.png"
        myDir = File(myDir, fileName)
        val out = FileOutputStream(myDir)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()
        openPhotoInGallery(myDir)
    }

    fun openPhotoInGallery(file: File) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(file.toString()), "image/*")
        startActivity(intent)

    }

    fun shareQrCode() {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/myStoreApp")
        val fileName = "MyStoreQr.png"
        val newFile = File(myDir, fileName)
        val contentUri: Uri? =
            FileProvider.getUriForFile(this, "com.ys.storeapp.contentprovider", newFile)
        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.setDataAndType(contentUri, contentResolver.getType(contentUri))
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            startActivity(Intent.createChooser(shareIntent, "Qr Share"))
        }
    }

    fun openGallery(request: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_OPEN_GALLERY -> {
                    binding.imgShop.visibility = View.VISIBLE
                    binding.imgShop.setImageURI(data!!.data)
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                }
                REQUEST_CODE_ASK_PERMISSIONS -> {
                    showToast("Permission granted")
                }
            }
        }
    }


}