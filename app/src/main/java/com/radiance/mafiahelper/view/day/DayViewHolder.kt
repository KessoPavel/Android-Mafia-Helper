package com.radiance.mafiahelper.view.day

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.day_item.view.*

class DayViewHolder(view: View, private val viewModel: DayViewModel): Holder(view = view) {
    private lateinit var holder: PlayerHolder

    override fun bind(holder: PlayerHolder) {
        this.holder = holder

        view.d_alias.text = holder.pseudonym
        view.d_name.text = holder.name

        view.d_in_vote.isChecked =  holder.inVoting
        view.d_in_vote.setOnClickListener { viewModel.playerInNomination(this.holder.player) }
    }
}