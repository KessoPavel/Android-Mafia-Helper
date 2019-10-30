package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.click
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_player_list_item.view.*

class PlayerListHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener, View.OnLongClickListener {
    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    override fun bind(provider: BasePlayerProvider) {
        super.bind(provider)

        view.fpli_name.text = provider.name
        view.fpli_statistic.text = provider.statistic

        if (provider.isSelected){
            active()
        } else {
            inactive()
        }
    }

    override fun onClick(p0: View?) {
        view.click(0.95f, 1.005f, 225)
        listener.onClick(provider)
    }

    override fun onLongClick(v: View?): Boolean {
        listener.onLongClick(provider)
        return true
    }

    private fun active() {
        view.fpli_image.setImageResource(provider.activeIcon)
        view.fpli_frame.background = provider.activeBackground
    }

    private fun inactive(){
        view.fpli_image.setImageResource(provider.inactiveIcon)
        view.fpli_frame.background = provider.inactiveBackground
    }
}