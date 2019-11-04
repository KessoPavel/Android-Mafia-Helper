package com.radiance.mafiahelper.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.player.PlayerHolder

abstract class Holder(protected val view: View): RecyclerView.ViewHolder(view){
    abstract fun bind(holder: PlayerHolder)
}