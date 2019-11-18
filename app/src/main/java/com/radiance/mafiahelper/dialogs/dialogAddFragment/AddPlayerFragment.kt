package com.radiance.mafiahelper.dialogs.dialogAddFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.player.Player
import kotlinx.android.synthetic.main.fragment_add_player.*

class AddPlayerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fap_main.setOnClickListener{
            Log.d("AddPlayerFragment", "Click")
        }
        fap_ok.setOnClickListener{
            fap_name.clearFocus()

            val name = fap_name.text.toString()
            if (name == "")
                return@setOnClickListener

            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            val direction = AddPlayerFragmentDirections.newPlayerAdded(Player(name))
            findNavController().navigate(direction)
        }
        fap_exit.setOnClickListener{
            fap_name.clearFocus()
            findNavController().popBackStack()
        }
    }
}