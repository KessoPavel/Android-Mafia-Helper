package com.radiance.mafiahelper.view.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.day_fragment.*

class DayFragment : Fragment() {
    private lateinit var viewModel: DayViewModel
    private var game = emptyGame()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.day_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = DayFragmentArgs.fromBundle(it)
            game = args.game
        }

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this)).get(DayViewModel::class.java)
        viewModel.currentPLayer.observe(this, Observer { nextPlayer() })
        viewModel.playersInNomination.observe(this, Observer { nextPlayer() })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameStatusChanged(gameIsReady) })
        viewModel.init(game, findNavController())

        adapter = RecyclerAdapter(players = createPlayerHolders(), holderBuilder = DayViewHolderBuilder(viewModel))
        d_recycler.layoutManager = LinearLayoutManager(context)
        d_recycler.adapter = adapter

        d_next_player.setOnClickListener{
            viewModel.nextPlayer()
        }
        d_play.setOnClickListener{
            val direction = DayFragmentDirections.startVoting(viewModel.createGame())
            viewModel.startVoting(direction = direction)
        }
    }

    private fun gameStatusChanged(gameIsReady: Boolean?) {
        gameIsReady?.let {
            d_next_player.visibility = if (it) View.INVISIBLE else View.VISIBLE
            d_play.isClickable = gameIsReady
        }
    }

    private fun nextPlayer() {
        viewModel.currentPLayer.value?.let {
            adapter.setData(createPlayerHolders())
            adapter.notifyDataSetChanged()
            d_current_player_alias.text = it.pseudonym
        }
    }

    private fun createPlayerHolders(): ArrayList<PlayerHolder> {
        val answer = ArrayList<PlayerHolder>()

        for (player in viewModel.playersInVoting.value!!) {
            if (!viewModel.currentPLayer.value!!.equals(player)) {
                answer.add(
                    PlayerHolder(player = player, inVoting = true)
                )
            }
        }

        for (player in viewModel.players.value!!) {
            if (!viewModel.currentPLayer.value!!.equals(player)) {
                if (!viewModel.playersInVoting.value!!.contains(player)) {
                    answer.add(
                        PlayerHolder(
                            player = player,
                            inVoting = viewModel.playersInNomination.value!!.contains(player)
                        )
                    )
                }
            }
        }

        return answer
    }

    private class DayViewHolderBuilder(private val viewModel: DayViewModel):
        HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflateView = parent.inflate(R.layout.day_item, false)
            return DayViewHolder(inflateView, viewModel)
        }
    }
}
