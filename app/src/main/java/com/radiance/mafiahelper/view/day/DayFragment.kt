package com.radiance.mafiahelper.view.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.enter
import com.radiance.mafiahelper.out
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.day_fragment.*

class DayFragment : Fragment() {
    private val viewModel: DayViewModel by viewModels()
    private var game = Game()
    private lateinit var adapter: RecyclerAdapter
    private var savedIsReady = true
    private var savedIsPlayersEnd= true


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
            //game = args.game
        }

//        viewModel = ViewModelProvider(this, SavedStateVMFactory(this)).get(DayViewModel::class.java)

        viewModel.currentPLayer.observe(this, Observer { nextPlayer() })
        viewModel.playersInNomination.observe(this, Observer { nextPlayer() })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameStatusChanged(gameIsReady) })
        viewModel.playersEnd.observe(this, Observer { playerEnd -> playersEndUpdate(playerEnd) })

        viewModel.init(game)

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

    private fun gameStatusChanged(isReady: Boolean) {
        d_play.isClickable = isReady

        if (savedIsReady != isReady){
            savedIsReady = isReady

            if (isReady) {
                d_play.enter()
            } else {
                d_play.out()
            }
        }
    }

    private fun playersEndUpdate(isReady: Boolean) {
        d_next_player.isClickable = !isReady

        if (savedIsPlayersEnd != isReady){
            savedIsPlayersEnd = isReady

            if (isReady) {
                d_next_player.out()
            } else {
                d_next_player.enter()
            }
        }
    }


    private fun nextPlayer() {
        viewModel.currentPLayer.value?.let {
            adapter.setData(createPlayerHolders())
            adapter.notifyDataSetChanged()
            d_current_player_alias.text = it.pseudonym
        }
    }


    private class DayViewHolderBuilder(private val viewModel: DayViewModel):
        HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflateView = parent.inflate(R.layout.day_item, false)
            return DayViewHolder(inflateView, viewModel)
        }
    }
}