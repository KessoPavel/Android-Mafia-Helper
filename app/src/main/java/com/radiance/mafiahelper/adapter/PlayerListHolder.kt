package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.click
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_player_list_item.view.*

class PlayerListHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener), View.OnClickListener, View.OnLongClickListener {
    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    override fun bind(holder: PlayerHolder) {
        super.bind(holder)

        view.fpli_name.text = holder.name
        view.fpli_statistic.text = holder.statistic

        if (holder.isSelected){
            active()
        } else {
            inactive()
        }
    }

    override fun onClick(p0: View?) {
        view.click(0.95f, 1.005f, 225)
        listener.onClick(holder)
    }

    override fun onLongClick(v: View?): Boolean {
        listener.onLongClick(holder)
        return true
    }

    private fun active() {
        view.fpli_image.setImageResource(holder.activeIcon)
        view.fpli_frame.background = holder.activeBackground
    }

    private fun inactive(){
        view.fpli_image.setImageResource(holder.inactiveIcon)
        view.fpli_frame.background = holder.inactiveBackground
    }
}