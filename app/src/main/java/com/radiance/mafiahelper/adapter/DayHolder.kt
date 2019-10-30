package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_day_item.view.*

class DayHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    override fun bind(holder: PlayerHolder) {
        super.bind(holder)

        view.fdi_number.text = holder.number
        view.fdi_name.text = holder.name
        view.fdi_pseudonym.text = holder.pseudonym
        view.fdi_status.text = holder.status
    }

    override fun onClick(v: View?) {
        listener.onClick(playerHolder = holder)
    }
}