package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_voting_item.view.*

class VotingHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener) {
    override fun bind(provider: BasePlayerProvider) {
        super.bind(provider)

        view.fvi_count.text = provider.votingCount
        view.fvi_name.text = provider.name
        view.fvi_pseudonym.text = provider.pseudonym
    }
}