package com.ys.storeapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.storeapp.exceptions.UnauthorizedException
import com.ys.storeapp.util.toLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

open class BaseViewModel() : ViewModel() {


    protected val _viewState = MutableLiveData<ViewState>(ViewState.Idle)
    val viewState = _viewState.toLiveData()


    fun launch(
        code: suspend CoroutineScope.() -> Unit
    ) {
        (viewModelScope + exceptionHandler).launch {
            code.invoke(this)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleFailure(throwable = exception)
    }

    private fun handleFailure(throwable: Throwable?) {
        if (throwable is UnauthorizedException) {
            _viewState.postValue(ViewState.Error(throwable))
        }
        _viewState.postValue(ViewState.Error(throwable))
    }

}