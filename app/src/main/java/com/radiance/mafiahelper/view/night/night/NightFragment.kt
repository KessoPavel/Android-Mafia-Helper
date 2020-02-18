package com.radiance.mafiahelper.view.night.night

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.setUpToolbar
import com.radiance.mafiahelper.view.night.night.adapter.NightAdapter
import com.radiance.mafiahelper.view.night.night.adapter.NightItem
import kotlinx.android.synthetic.main.night_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_dark.*

class NightFragment : Fragment() {
    private val viewModel: NightViewModel by viewModels()
    private var game = Game()
    private val adapter: NightAdapter by lazy { NightAdapter(createPlayerHolders(), viewModel) }

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

        viewModel.currentPlayer.observe(this, androidx.lifecycle.Observer { updateCurrentPlayer() })

        n_recycler.layoutManager = LinearLayoutManager(context)
        n_recycler.adapter = adapter

        viewModel.init(game)

        n_play.setOnClickListener {
            val direction: NavDirections

            if (game.gameOptions.sheriffInGame) {
                direction = NightFragmentDirections.sheriffCheck(viewModel.createGame())
            } else if (game.gameOptions.doctorInGame) {
                direction = NightFragmentDirections.doctorChoice(viewModel.createGame())
            } else {
                direction = NightFragmentDirections.endNight(viewModel.createGame())
            }
            findNavController().navigate(direction)
        }

        setUpToolbar(mainToolbarDark, R.string.night)
    }

    private fun updateCurrentPlayer() {
        adapter.characters = createPlayerHolders()
        adapter.notifyDataSetChanged()
    }

    private fun createPlayerHolders(): ArrayList<NightItem> {
        val answer = ArrayList<NightItem>()

        for (player in viewModel.players) {
            answer.add(
                NightItem(
                    player, player == viewModel.currentPlayer.value
                )
            )
        }

        return answer
    }


}
