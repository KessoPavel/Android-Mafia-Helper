package com.radiance.mafiahelper.playerList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.player.Player
import kotlinx.android.synthetic.main.fragment_player_info.view.*
import kotlinx.android.synthetic.main.fragment_player_list_item.view.*

class PlayerListAdapter(private val players: Array<Player>, context: Context): RecyclerView.Adapter<PlayerListAdapter.PlayerHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerHolder {
        val inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
        return PlayerHolder(inflationView)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindPlayer(player = players[position])
    }

    class PlayerHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var player: Player? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
        }

        fun bindPlayer(player: Player){
            this.player = player

            view.player_name.text = player.name
            view.v_l.text = "${player.victoriesForBlacks + player.victoriesForReds + player.victoriesPerDoctor + player.victoriesPerSheriff} / " +
                    "${player.gamesForBlacks + player.gamesForReds + player.gamesPerDoctor + player.gamesPerSheriff}"
            view.player_image.setImageResource(R.drawable.outline_person_add_black_18dp)
        }
    }
}