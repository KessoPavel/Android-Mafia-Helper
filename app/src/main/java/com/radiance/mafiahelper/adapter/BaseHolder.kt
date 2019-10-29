package com.radiance.mafiahelper.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.playerProvider.BasePlayerProvider
import com.radiance.mafiahelper.playerProvider.DayDisplayManager
import kotlinx.android.synthetic.main.fragment_day_item.view.*

class BaseHolder(val view: View, val listener: Adapter.ClickListener): RecyclerView.ViewHolder(view) {
    fun bind(provider: BasePlayerProvider){

    }
}