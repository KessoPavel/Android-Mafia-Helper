package com.radiance.mafiahelper.view.playersPicker

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.player.PlayersManager
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter

class PlayersPickerViewModel : ViewModel() {
    private val game = Game()
    private lateinit var players: ArrayList<Player>
    lateinit var adapter: RecyclerAdapter
    private lateinit var activeBackground: Drawable
    private lateinit var passiveBackground: Drawable

    fun init(activeBackground: Drawable, passiveBackground: Drawable){
        this.activeBackground = activeBackground
        this.passiveBackground = passiveBackground
        players = PlayersManager.loadPlayers()
        adapter = RecyclerAdapter(players = convertPlayerToPlayerHolder(players), holderBuilder = PlayerPickerViewHolderBuilder(this))
    }

    fun onPlayerClick(player: Player){
        if (game.players.contains(player)){
            game.removePlayer(player)
        } else {
            game.addPlayer(player)
        }
        adapter.setData(convertPlayerToPlayerHolder(players))
        adapter.notifyDataSetChanged()
    }

    fun onAddPlayerClick(){
        val player = Player(name = "322")
        players.add(player)

        adapter.setData(convertPlayerToPlayerHolder(players))
        adapter.notifyDataSetChanged()
    }

    fun onPlayerLongClick(player: Player){

    }

    fun onPlayClick(navController: NavController){

    }

    private fun convertPlayerToPlayerHolder(players: ArrayList<Player>): ArrayList<PlayerHolder>{
        val answer = ArrayList<PlayerHolder>()

        for (player in players){
            answer.add(PlayerHolder(player = player, background = getBackGround(player, game), icon = getIcon(player), isSelected = game.players.contains(player)))
        }

        return answer
    }

    private fun getIcon(player: Player): Int {
        return when {
            player.isBestBlack -> R.drawable.mafia_gray
            player.isBestRed -> R.drawable.red_gray
            player.isBestDoctor -> R.drawable.doctor_gray
            player.isBestSheriff -> R.drawable.sheriff_gray
            else -> R.drawable.empty
        }
    }

    private fun getBackGround(player: Player, game: Game): Drawable {
        return if (game.players.contains(player)) activeBackground else passiveBackground
    }

    class PlayerPickerViewHolderBuilder(private val viewModel: PlayersPickerViewModel): HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflationView = parent.inflate(R.layout.player_picker_item, false)
            return PlayerPickerViewHolder(inflationView, viewModel)
        }

    }
}