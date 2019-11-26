package com.radiance.mafiahelper.view.playersPicker

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.player_picker_item.view.*
import java.util.*

class PlayerPickerViewHolder(view: View, private val viewModel: PlayersPickerViewModel): Holder(view), View.OnClickListener, View.OnLongClickListener {
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
        view.icon.background = holder.activeBackground
        if (holder.isSelected) {
            view.icon_title.text = ""
        } else {
            view.icon_title.text = holder.name[0].toString().toUpperCase()
        }
    }

    override fun onClick(p0: View?) {
        viewModel.onPlayerClick(holder.player)
    }

    override fun onLongClick(v: View?): Boolean {
        val direction = PlayersPickerDirections.showPlayerInfo(holder.player)
        viewModel.onPlayerLongClick(direction)
        return true
    }
}