package com.radiance.mafiahelper.view.aliasPicker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.game.Game

class AliasPicker : Fragment() {
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
    }

}
