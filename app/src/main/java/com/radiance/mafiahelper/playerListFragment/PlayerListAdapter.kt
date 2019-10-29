package com.radiance.mafiahelper.playerListFragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.click
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.playerProvider.PlayerListDisplayManager
import kotlinx.android.synthetic.main.fragment_player_list_item.view.*


class PlayerListAdapter(
    private var players: ArrayList<Player>,
    private val listener: ListItemListener,
    context: Context) : RecyclerView.Adapter<PlayerListAdapter.PlayerHolder>() {

    private val inactiveBackground: Drawable = ContextCompat.getDrawable(context, R.drawable.rounded_figure)!!
    private val activeBackground = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected)
    private val activeBackgroundRed = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_red)
    private val activeBackgroundBlack = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_black)
    private val activeBackgroundSheriff = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_detective)
    private val activeBackgroundDoctor = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_doctor)
    private val activeIconRed = R.drawable.red
    private val inactiveIconRed = R.drawable.red_gray
    private val activeIconBlack = R.drawable.mafia
    private val inactiveIconBlack = R.drawable.mafia_gray
    private val activeIconSheriff = R.drawable.sheriff
    private val inactiveIconSheriff = R.drawable.sheriff_gray
    private val activeIconDoctor = R.drawable.doctor
    private val inactiveIconDoctor = R.drawable.doctor_gray
    private val emptyIcon = R.drawable.empty

    private var displayPlayers = createDisplayManagers()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
        return PlayerHolder(inflationView, listener)
    }

    override fun getItemCount() = displayPlayers.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindPlayer(displayManager = displayPlayers[position])
    }

    fun addPlayer(player: Player){
        players.add(player)

        val builder = PlayerListDisplayManager.Builder(player= player)
        builder.inactiveBackground = inactiveBackground
        builder.activeBackground = activeBackground
        builder.activeIcon = emptyIcon
        builder.inactiveIcon = emptyIcon

        if (player.isBestBlack){
            builder.activeBackground = activeBackgroundBlack
            builder.activeIcon = activeIconBlack
            builder.inactiveIcon = inactiveIconBlack
        } else if (player.isBestRed) {
            builder.activeBackground = activeBackgroundRed
            builder.activeIcon = activeIconRed
            builder.inactiveIcon = inactiveIconRed
        } else if (player.isBestDoctor){
            builder.activeBackground = activeBackgroundDoctor
            builder.activeIcon = activeIconDoctor
            builder.inactiveIcon = inactiveIconDoctor
        } else if (player.isBestSheriff) {
            builder.activeBackground = activeBackgroundSheriff
            builder.activeIcon = activeIconSheriff
            builder.inactiveIcon = inactiveIconSheriff
        }

        displayPlayers.add(builder.build())
    }

    private fun createDisplayManagers(): ArrayList<PlayerListDisplayManager>{
        val answer = ArrayList<PlayerListDisplayManager>()

        for (player in players){
            val builder = PlayerListDisplayManager.Builder(player= player)
            builder.inactiveBackground = inactiveBackground
            builder.activeBackground = activeBackground
            builder.activeIcon = emptyIcon
            builder.inactiveIcon = emptyIcon

            if (player.isBestBlack){
                builder.activeBackground = activeBackgroundBlack
                builder.activeIcon = activeIconBlack
                builder.inactiveIcon = inactiveIconBlack
            } else if (player.isBestRed) {
                builder.activeBackground = activeBackgroundRed
                builder.activeIcon = activeIconRed
                builder.inactiveIcon = inactiveIconRed
            } else if (player.isBestDoctor){
                builder.activeBackground = activeBackgroundDoctor
                builder.activeIcon = activeIconDoctor
                builder.inactiveIcon = inactiveIconDoctor
            } else if (player.isBestSheriff) {
                builder.activeBackground = activeBackgroundSheriff
                builder.activeIcon = activeIconSheriff
                builder.inactiveIcon = inactiveIconSheriff
            }

            answer.add(builder.build())
        }

        return answer
    }

    class PlayerHolder(v: View, l: ListItemListener) : RecyclerView.ViewHolder(v), View.OnClickListener, View.OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        private var view: View = v
        private val listener: ListItemListener = l
        private lateinit var displayManager: PlayerListDisplayManager

        init {
            v.setOnClickListener(this)
            v.setOnLongClickListener(this)
        }



        fun bindPlayer(displayManager: PlayerListDisplayManager){
            this.displayManager = displayManager

            view.fpli_name.text = displayManager.name
            view.fpli_statistic.text = displayManager.statistic

            if (displayManager.isSelected){
                active()
            } else {
                inactive()
            }
        }

        private fun active() {
            view.fpli_image.setImageResource(displayManager.activeIcon)
            view.fpli_frame.background = displayManager.activeBackground
        }

        private fun inactive(){
            view.fpli_image.setImageResource(displayManager.inactiveIcon)
            view.fpli_frame.background = displayManager.inactiveBackground
        }
    }
}