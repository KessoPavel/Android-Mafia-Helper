package com.radiance.mafiahelper.playerListFragment

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayersManager
import kotlinx.android.synthetic.main.fragment_player_list_item.view.*


class PlayerListAdapter(
    private val players: Array<Player>,
    private val listener: PlayerListFragmentListener) : RecyclerView.Adapter<PlayerListAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
        return PlayerHolder(inflationView, listener)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindPlayer(player = players[position])
    }

    class PlayerHolder(v: View, l: PlayerListFragmentListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private val listener: PlayerListFragmentListener = l
        private lateinit var player: Player

        private val AN_SELECTED_BACKGRAUND = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure)
        private val SELECTED_BACKGRAUND = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected)
        private val SELECTED_BACKGRAUND_RED = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_red)
        private val SELECTED_BACKGRAUND_BLACK = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_black)
        private val SELECTED_BACKGRAUND_SHERIFF = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_detective)
        private val SELECTED_BACKGRAUND_DOCTOR = ContextCompat.getDrawable(view.context, R.drawable.rounded_figure_selected_doctor)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            view.click(0.95f, 1.005f, 225)

            if (player.isSelecter) {
                notSelectedStyle()
                listener.onPlayerUnSelect(player)
            } else {
                selectedStyle()
                listener.onPlayerSelect(player)
            }
            player.isSelecter = !player.isSelecter
        }

        fun bindPlayer(player: Player){
            this.player = player

            view.fpli_name.text = player.name
            view.fpli_statistic.text = PlayersManager.getStatistic(player)

            if (player.isSelecter){
                selectedStyle()
            } else {
                notSelectedStyle()
            }
        }

        private fun selectedStyle(){
            when {
                player.isBestRed -> {
                    view.fpli_image.setImageResource(R.drawable.red)
                    view.fpli_frame.background = SELECTED_BACKGRAUND_RED
                }
                player.isBestBlack -> {
                    view.fpli_image.setImageResource(R.drawable.mafia)
                    view.fpli_frame.background = SELECTED_BACKGRAUND_BLACK
                }
                player.isBestSheriff -> {
                    view.fpli_image.setImageResource(R.drawable.sheriff)
                    view.fpli_frame.background = SELECTED_BACKGRAUND_SHERIFF
                }
                player.isBestDoctor -> {
                    view.fpli_image.setImageResource(R.drawable.doctor)
                    view.fpli_frame.background = SELECTED_BACKGRAUND_DOCTOR
                }
                else -> view.fpli_frame.background = SELECTED_BACKGRAUND
            }
        }

        private fun notSelectedStyle(){
            view.fpli_frame.background = AN_SELECTED_BACKGRAUND

            when {
                player.isBestRed ->
                    view.fpli_image.setImageResource(R.drawable.red_gray)
                player.isBestBlack ->
                    view.fpli_image.setImageResource(R.drawable.mafia_gray)
                player.isBestSheriff ->
                    view.fpli_image.setImageResource(R.drawable.sheriff_gray)
                player.isBestDoctor ->
                    view.fpli_image.setImageResource(R.drawable.doctor_gray)
            }
        }
    }
}