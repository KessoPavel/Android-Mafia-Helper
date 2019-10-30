package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_getting_to_known_item.view.*

class GettingToKnownHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    override fun bind(holder: PlayerHolder) {
        super.bind(holder)

        view.fgtki_name.text = holder.name
        view.fgtki_pseudonym.text = holder.pseudonym
        view.fgtki_number.text = holder.number
    }

    override fun onClick(v: View?) {
        listener.onClick(playerHolder = holder)
    }
}