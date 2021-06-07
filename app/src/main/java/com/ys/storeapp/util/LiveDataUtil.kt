package com.ys.storeapp.util
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> = this