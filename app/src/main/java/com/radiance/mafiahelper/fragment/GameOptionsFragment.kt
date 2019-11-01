package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game_options.*

class GameOptionsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_game_options
    override val title: Int = R.string.select_game_options

    private var game: Game = Game()

    companion object {
        const val TAG = "GameOptionsFragment"

        fun newInstance(game: Game): GameOptionsFragment {
            val fragment = GameOptionsFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        game = arguments?.getSerializable("GAME") as Game

        fgo_number_piker.setOnValueChangedListener { ignored0, ignored1, newVal ->
            game.blackCount = newVal
            updateCounts()
        }

        fgo_doctor_check_box.setOnCheckedChangeListener { ignored, isChecked ->
            game.doctorInGame = isChecked
            updateCounts()

        }

        fgo_sheriff_check_box.setOnCheckedChangeListener { ignored, isChecked ->
            game.sheriffInGame = isChecked
            updateCounts()
        }

        fgo_start_game.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("GAME", game)
            findNavController().navigate(R.id.action_gameOptionsFragment_to_gettingToKnowFragment, bundle)
            listener.startGettingToKnown(game)
        }

        fgo_number_piker.maxValue = game.maxBlackCount
        fgo_number_piker.minValue = game.minBlackCount

        game.blackCount = game.minBlackCount

        fgo_toolbar.text =  "${getString(R.string.select_game_options)} | ${game.playersCont} players"

        updateCounts()
    }

    private fun updateCounts(){
        fgo_black_count.text = game.blackCount.toString()
        fgo_red_count.text = (game.playersCont - (game.blackCount  + (if (game.doctorInGame) 1 else 0) + (if (game.sheriffInGame) 1 else 0))).toString()
        fgo_sheriff_count.text = (if (game.sheriffInGame) 1 else 0).toString()
        fgo_doctor_count.text = (if (game.doctorInGame) 1 else 0).toString()
    }
}