package com.radiance.mafiahelper.playerList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.player.Player
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
        private val SELECTED_BACKGRAUND_RED = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_red)
        private val SELECTED_BACKGRAUND_BLACK = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_black)
        private val SELECTED_BACKGRAUND_DETECTIVE = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_detective)
        private val SELECTED_BACKGRAUND_DOCTOR = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_doctor)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            view.click(0.95f, 1.005f, 225)

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
            if (player?.isBestRed!!){
                view.fpli_image.setImageResource(R.drawable.red)
                view.fpli_frame.background = SELECTED_BACKGRAUND_RED
            } else if (player?.isBestBlack!!) {
                view.fpli_image.setImageResource(R.drawable.mafia)
                view.fpli_frame.background = SELECTED_BACKGRAUND_BLACK
            } else if (player?.isBestDetective!!) {
                view.fpli_image.setImageResource(R.drawable.detective)
                view.fpli_frame.background = SELECTED_BACKGRAUND_DETECTIVE
            } else if (player?.isBestDoctor!!) {
                view.fpli_image.setImageResource(R.drawable.doctor)
                view.fpli_frame.background = SELECTED_BACKGRAUND_DOCTOR
            } else {
                view.fpli_frame.background = SELECTED_BACKGRAUND
            }
        }

        private fun notSelectedStyle(){
            view.fpli_frame.background = AN_SELECTED_BACKGRAUND

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