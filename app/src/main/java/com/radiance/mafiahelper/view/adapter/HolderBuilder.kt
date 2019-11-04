package com.radiance.mafiahelper.view.adapter

import android.view.View
import android.view.ViewGroup

interface HolderBuilder {
    fun build(parent: ViewGroup): Holder
}