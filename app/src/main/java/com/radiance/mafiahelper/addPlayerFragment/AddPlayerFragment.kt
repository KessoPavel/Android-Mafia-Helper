package com.radiance.mafiahelper.addPlayerFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.player.Player
import kotlinx.android.synthetic.main.fragment_add_player.*

class AddPlayerFragment: Fragment() {
    private lateinit var listener: AddPlayerListener

    companion object {
        fun newInstance(listener: AddPlayerListener): AddPlayerFragment {
            val fragment = AddPlayerFragment()
            fragment.listener = listener
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_player, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fap_main.setOnClickListener{_ ->
            Log.d("AddPlayerFragment", "Click")
        }
        fap_ok.setOnClickListener{_ ->
            fap_name.clearFocus()

            val name = fap_name.text.toString()
            if (name == "")
                return@setOnClickListener

            listener.playerAdded(Player(name= name))
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        fap_exit.setOnClickListener{_ ->
            fap_name.clearFocus()
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }
}