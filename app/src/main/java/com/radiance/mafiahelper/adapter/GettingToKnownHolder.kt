package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_getting_to_known_item.view.*

class GettingToKnownHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    override fun bind(provider: BasePlayerProvider) {
        super.bind(provider)

        view.fgtki_name.text = provider.name
        view.fgtki_pseudonym.text = provider.pseudonym
        view.fgtki_number.text = provider.number
    }

    override fun onClick(v: View?) {
        listener.onClick(basePlayerProvider = provider)
    }
}