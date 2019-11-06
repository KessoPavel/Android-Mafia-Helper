package com.radiance.mafiahelper.tools

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MutableLiveObject<T : BaseObservable>: MutableLiveData<T>() {
    private val callback = object: Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            value = value
        }
    }

    override fun setValue(value: T?) {
        super.setValue(value)

        value?.addOnPropertyChangedCallback(callback)
    }
}