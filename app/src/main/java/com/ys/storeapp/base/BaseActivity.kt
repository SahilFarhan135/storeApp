package com.ys.storeapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ys.storeapp.BaseApplication
import com.ys.storeapp.exceptions.UnauthorizedException
import com.ys.storeapp.injection.component.AppComponent
import com.ys.storeapp.util.UiUtil
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    lateinit var navigator: Navigator
    protected lateinit var uiUtil: UiUtil


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent =
            (application as BaseApplication).appComponent
        injectActivity(appComponent)
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
        navigator = Navigator(this)
        uiUtil = UiUtil(this)
        observeViewState()
        addObservers()
    }

    private fun observeViewState() {
        viewModel.viewState
            .observe(this, {
                when (it) {
                    ViewState.Idle -> {
                    }
                    ViewState.Loading -> {
                    }
                    is ViewState.Success -> {
                        showToast(it.message)
                    }
                    is ViewState.Error -> {
                        handleException(it.throwable)
                    }
                }
            })
    }

    private fun handleException(throwable: Throwable?) {
        when (throwable) {
            is UnauthorizedException -> showToast(throwable.message)
            else -> showToast(throwable?.message)
        }
    }


    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
    }

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    abstract fun injectActivity(appComponent: AppComponent)

    abstract fun addObservers()


    protected fun showToast(
        message: String?,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        message?.let { uiUtil.showToast(it, duration) }
    }

    fun getLayoutBinding() = binding


}