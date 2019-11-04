package com.radiance.mafiahelper.view.playersPicker

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.player_picker_item.view.*

class PlayerPickerViewHolder(view: View,private val viewModel: PlayersPickerViewModel): Holder(view), View.OnClickListener, View.OnLongClickListener {
    private lateinit var holder: PlayerHolder

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    override fun bind(holder: PlayerHolder) {
        this.holder = holder

        view.ppi_name.text = holder.name
        view.ppi_statistic.text = holder.statistic
        view.ppi_image.setImageResource(holder.icon)
        view.ppi_frame.background = holder.background
    }

    override fun onClick(p0: View?) {
        viewModel.onPlayerClick(holder.player)
    }

    override fun onLongClick(v: View?): Boolean {
        viewModel.onPlayerLongClick(holder.player)
        return true
    }

}