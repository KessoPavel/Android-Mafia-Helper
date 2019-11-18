package com.radiance.mafiahelper.view.aliasPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.alias_picker_fragment.*
import java.util.*

class AliasPicker : Fragment() {
    lateinit var adapter: RecyclerAdapter
    private lateinit var viewModel: AliasPickerViewModel
    private var game: Game = emptyGame()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alias_picker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = AliasPickerArgs.fromBundle(it)
            game = args.game
        }

        viewModel =
            ViewModelProvider(this, SavedStateVMFactory(this)).get(AliasPickerViewModel::class.java)
        viewModel.init(game, findNavController())
        viewModel.players.observe(this, Observer { adapter.setData(covertPlayerToPlayerHolder()) })

        val move = DragAndDrop(this)
        val touchHelper = ItemTouchHelper(move)
        touchHelper.attachToRecyclerView(ap_recycler)
        adapter = RecyclerAdapter(players = covertPlayerToPlayerHolder(), holderBuilder = AliasPickerViewHolderBuilder(viewModel, touchHelper))
        ap_recycler.layoutManager = LinearLayoutManager(context)
        ap_recycler.adapter = adapter

        ap_play.setOnClickListener{
            val game = viewModel.createGame()
            val direction = AliasPickerDirections.gotToFirstNight(game)
            viewModel.goToFirstNight(direction)
        }
    }

    fun onItemMove(from: Int, to: Int){
        viewModel.changePlayerPosition(from, to)
        adapter.notifyItemMoved(from, to)
    }

    private fun covertPlayerToPlayerHolder(): ArrayList<PlayerHolder> {
        val answer = ArrayList<PlayerHolder>()

        viewModel.players.value?.let {
            for (player: Player in viewModel.players.value!!){
                answer.add(
                    PlayerHolder(
                        player= player,
                        pseudonym = player.pseudonym
                    )
                )
            }
        }

        return answer
    }


    private class AliasPickerViewHolderBuilder(private val viewModel: AliasPickerViewModel,
                                               private val touchHelper: ItemTouchHelper):
            HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflateView = parent.inflate(R.layout.alias_picker_item, false)
            return ViewHolder(inflateView, viewModel, touchHelper)
        }

    }
}
