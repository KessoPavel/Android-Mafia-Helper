package com.radiance.mafiahelper.view.playersPicker

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.fragment.GameOptionsFragmentDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.players_picker_fragment.*
import java.nio.file.OpenOption

class PlayersPicker : Fragment() {
    lateinit var adapter: RecyclerAdapter
    private lateinit var activeBackground: Drawable
    private lateinit var passiveBackground: Drawable

    private lateinit var viewModel: PlayersPickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_picker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activeBackground = context?.getDrawable(R.drawable.list_item)!!
        passiveBackground = context?.getDrawable(R.drawable.unselected_list_item)!!

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this))
            .get(PlayersPickerViewModel::class.java)

        viewModel.init(findNavController())

        viewModel.players.observe(this, Observer { _ -> playersUpdated() })
        viewModel.playersInGame.observe(this, Observer { _ -> playersUpdated() })

        adapter = RecyclerAdapter(players = convertPlayerToPlayerHolder() , holderBuilder = PlayerPickerViewHolderBuilder(viewModel))
        pp_recycler.layoutManager = LinearLayoutManager(context)
        pp_recycler.adapter = adapter

        pp_add_player.setOnClickListener{
            val direction = PlayersPickerDirections.addNewPlayer()
            viewModel.onAddPlayerClick(direction)
        }

        pp_play.setOnClickListener{
            val direction = PlayersPickerDirections.openGameOptions(viewModel.game())
            viewModel.onPlayClick(direction)
        }

        arguments?.let {
            val saveArgs = PlayersPickerArgs.fromBundle(it)
            val player = saveArgs.newPlayer

            viewModel.addPlayer(player)
        }
    }

    private fun playersUpdated(){
        adapter.setData(convertPlayerToPlayerHolder())
        adapter.notifyDataSetChanged()
    }

    private fun convertPlayerToPlayerHolder(): ArrayList<PlayerHolder>{
        val answer = ArrayList<PlayerHolder>()

        for (player in viewModel.players.value!!){
            answer.add(
                PlayerHolder(player = player,
                    background = getBackGround(player, viewModel.playersInGame.value!!),
                    icon = getIcon(player),
                    isSelected = viewModel.playersInGame.value!!.contains(player)))
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

    private fun getBackGround(player: Player, game: ArrayList<Player>): Drawable {
        return if (game.contains(player)) activeBackground else passiveBackground
    }


    private class PlayerPickerViewHolderBuilder(private val viewModel: PlayersPickerViewModel):
        HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflationView = parent.inflate(R.layout.player_picker_item, false)
            return PlayerPickerViewHolder(inflationView, viewModel)
        }

    }
}
