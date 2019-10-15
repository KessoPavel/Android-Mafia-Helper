package com.radiance.mafiahelper.votingFragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.playerDisplayManager.VotingDisplayManager
import kotlinx.android.synthetic.main.fragment_voting_item.view.*

class VotingAdapter(
    private var players: ArrayList<VotingDisplayManager>
)
    :RecyclerView.Adapter<VotingAdapter.PlayerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotingAdapter.PlayerHolder {
        val view = parent.inflate(R.layout.fragment_voting_item, false)
        return PlayerHolder(view)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: VotingAdapter.PlayerHolder, position: Int) {
        holder.bindManager(players[position])
    }

    fun setData(newPlayers: ArrayList<VotingDisplayManager>){
        players = newPlayers
    }

    class PlayerHolder(val view: View): RecyclerView.ViewHolder(view) {
        private lateinit var manager: VotingDisplayManager

        fun bindManager(votingDisplayManager: VotingDisplayManager) {
            manager = votingDisplayManager

            view.fvi_count.text = manager.count
            view.fvi_name.text = manager.name
            view.fvi_pseudonym.text = manager.pseudonym
        }
    }
}