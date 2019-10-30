package com.radiance.mafiahelper.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.player.PlayerHolder

open class BaseHolder(val view: View, val listener: Adapter.HolderListener): RecyclerView.ViewHolder(view) {
    protected lateinit var holder: PlayerHolder

    open fun bind(holder: PlayerHolder){
        this.holder = holder
    }
}