package com.radiance.mafiahelper.view.night

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.night_item.view.*

class NightViewHolder(view: View,private val viewModel: NightViewModel) : Holder(view) {

    override fun bind(holder: PlayerHolder) {
        view.n_name.text = holder.name
        view.n_alias.text = holder.pseudonym
        view.n_is_death.isChecked = holder.isSelected

        view.n_is_death.setOnClickListener {
            viewModel.selectPlayer(holder.player)
        }
    }
}
