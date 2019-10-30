package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import kotlinx.android.synthetic.main.fragment_game_options.*

class GameOptionsFragment : GameFragment() {
    override val layoutId: Int = R.layout.fragment_game_options

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        return view
    }

    private lateinit var game: Game

    companion object {
        const val TAG = "GameOptionsFragment"

        fun newInstance(game: Game): GameOptionsFragment {
            val fragment = GameOptionsFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            listener.startGettingToKnown(game)
        }

        fgo_number_piker.maxValue = game.maxBlackCount
        fgo_number_piker.minValue = game.minBlackCount

        game.blackCount = game.minBlackCount

        updateCounts()
    }

    private fun updateCounts(){
        fgo_black_count.text = game.blackCount.toString()
        fgo_red_count.text = (game.playersCont - (game.blackCount  + (if (game.doctorInGame) 1 else 0) + (if (game.sheriffInGame) 1 else 0))).toString()
        fgo_sheriff_count.text = (if (game.sheriffInGame) 1 else 0).toString()
        fgo_doctor_count.text = (if (game.doctorInGame) 1 else 0).toString()
    }
}