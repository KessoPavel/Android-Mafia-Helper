package com.radiance.mafiahelper.playerList

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        private var isSelected = false

        private val AN_SELECTED_BACKGRAUND = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure)
        private val SELECTED_BACKGRAUND = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (isSelected) {
                notSelectedStyle()
            } else {
                selectedStyle()
            }
            isSelected = !isSelected
        }

        fun bindPlayer(player: Player){
            this.player = player

            view.fpli_name.text = player.name
            view.fpli_statistic.text = player.statistic

            notSelectedStyle()
        }

        private fun selectedStyle(){
            view.fpli_frame.background = SELECTED_BACKGRAUND

            if (player?.isBestRed!!){
                view.fpli_image.setImageResource(R.drawable.red)
            } else if (player?.isBestBlack!!) {
                view.fpli_image.setImageResource(R.drawable.mafia)
            } else if (player?.isBestDetective!!) {
                view.fpli_image.setImageResource(R.drawable.detective)
            } else if (player?.isBestDoctor!!) {
                view.fpli_image.setImageResource(R.drawable.doctor)
            }
        }

        private fun notSelectedStyle(){
            view.fpli_frame.background = AN_SELECTED_BACKGRAUND
            view.fpli_image.setImageResource(R.drawable.mafia_gray)


            if (player?.isBestRed!!){
                view.fpli_image.setImageResource(R.drawable.red_gray)
            } else if (player?.isBestBlack!!) {
                view.fpli_image.setImageResource(R.drawable.mafia_gray)
            } else if (player?.isBestDetective!!) {
                view.fpli_image.setImageResource(R.drawable.detective_gray)
            } else if (player?.isBestDoctor!!) {
                view.fpli_image.setImageResource(R.drawable.doctor_gray)
            }
        }

    }
}