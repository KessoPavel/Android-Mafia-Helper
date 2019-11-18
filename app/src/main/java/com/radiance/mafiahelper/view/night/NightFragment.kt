package com.radiance.mafiahelper.view.night

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.night_fragment.*

class NightFragment : Fragment() {
    private lateinit var viewModel: NightViewModel
    private var game = emptyGame()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.night_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = NightFragmentArgs.fromBundle(it)
            game = args.game
        }

        viewModel = ViewModelProviders.of(this).get(NightViewModel::class.java)
        viewModel.currentPlayer.observe(this, androidx.lifecycle.Observer { updateCurrentPlayer() })

        adapter = RecyclerAdapter(createPlayerHolders(), NightHolderBuilder(viewModel))
        n_recycler.layoutManager = LinearLayoutManager(context)
        n_recycler.adapter = adapter

        viewModel.init(game, findNavController())

        n_play.setOnClickListener{
            val direction = NightFragmentDirections.endNight(viewModel.createGame(), viewModel.currentPlayer.value)
            viewModel.endNight(direction)
        }
    }

    private fun updateCurrentPlayer() {
        adapter.setData(createPlayerHolders())
        adapter.notifyDataSetChanged()
    }

    private fun createPlayerHolders(): ArrayList<PlayerHolder> {
        val answer = ArrayList<PlayerHolder>()

        for (player in viewModel.players){
            answer.add(
                PlayerHolder(
                    player = player,
                    isSelected = player == viewModel.currentPlayer.value
                )
            )
        }

        return answer
    }

    private class NightHolderBuilder(private val viewModel: NightViewModel): HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflatedView = parent.inflate(R.layout.night_item, false)
            return NightViewHolder(inflatedView, viewModel)
        }
    }
}
