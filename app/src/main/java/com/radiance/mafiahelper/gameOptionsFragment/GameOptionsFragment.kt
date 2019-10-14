package com.radiance.mafiahelper.gameOptionsFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import kotlinx.android.synthetic.main.fragment_game_options.*

class GameOptionsFragment : Fragment() {
    private lateinit var listener: StartGameListener
    private lateinit var game: Game

    companion object {
        const val TAG = "GameOptionsFragment"

        fun newInstance(game: Game): GameOptionsFragment {
            val fragment = GameOptionsFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is StartGameListener){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement ListItemListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_options, container, false)
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
            listener.goToGettingToKnow(game)
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