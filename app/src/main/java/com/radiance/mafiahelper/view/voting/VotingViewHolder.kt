package com.radiance.mafiahelper.view.voting

import android.view.View
import android.view.ViewGroup
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.voting_item.view.*

class VotingViewHolder(view: View, private val viewModel: VotingViewModel) : Holder(view) {
    private lateinit var holder: PlayerHolder

    override fun bind(holder: PlayerHolder) {
        this.holder = holder

        view.v_alias.text = holder.pseudonym
        view.v_name.text = holder.name
        view.v_value.text = holder.votingCount
    }

}
