package com.radiance.mafiahelper.view.firstNight

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.view.aliasPicker.AliasPickerViewModel

class FirstNight : Fragment() {
    private var game = emptyGame()
    private lateinit var viewModel: FirstNightViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_night_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = FirstNightArgs.fromBundle(it)
            game = args.game
        }

        viewModel =
            ViewModelProvider(this, SavedStateVMFactory(this)).get(FirstNightViewModel::class.java)

        viewModel.init(game, findNavController())
    }

}
