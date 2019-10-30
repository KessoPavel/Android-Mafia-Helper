package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_day_item.view.*

class DayHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    override fun bind(provider: BasePlayerProvider) {
        super.bind(provider)

        view.fdi_number.text = provider.number
        view.fdi_name.text = provider.name
        view.fdi_pseudonym.text = provider.pseudonym
        view.fdi_status.text = provider.status
    }

    override fun onClick(v: View?) {
        listener.onClick(basePlayerProvider = provider)
    }
}