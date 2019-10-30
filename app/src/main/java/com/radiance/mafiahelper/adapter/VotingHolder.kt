package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_voting_item.view.*

class VotingHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener) {
    override fun bind(holder: PlayerHolder) {
        super.bind(holder)

        view.fvi_count.text = holder.votingCount
        view.fvi_name.text = holder.name
        view.fvi_pseudonym.text = holder.pseudonym
    }
}