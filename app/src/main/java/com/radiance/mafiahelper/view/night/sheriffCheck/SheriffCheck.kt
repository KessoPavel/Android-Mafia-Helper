package com.radiance.mafiahelper.view.night.sheriffCheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.bsvt.core.roleCount
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.view.night.night.NightFragmentArgs
import kotlinx.android.synthetic.main.sheriff_check_fragment.*

class SheriffCheck : Fragment() {
    private var game: Game = Game()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sheriff_check_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = NightFragmentArgs.fromBundle(it)
            game = args.game
        }

        if (game.characters.roleCount(Role.Sheriff) == 0) {
            sc_title.text = getString(R.string.sheriff_is_dead)
        } else {
            sc_title.text = getString(R.string.sheriff_check)
        }

        scf_play.setOnClickListener{
            val direction: NavDirections
            if (game.gameOptions.doctorInGame) {
                direction = SheriffCheckDirections.doctorChoce(game)
            } else {
                direction = SheriffCheckDirections.endNight(game)
            }

            findNavController().navigate(direction)
        }
    }
}
