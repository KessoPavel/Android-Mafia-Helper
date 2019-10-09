package com.radiance.mafiahelper.addPlayerFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.R

class AddPlayerFragment: Fragment() {
    companion object {
        fun newInstance(): AddPlayerFragment {
            return AddPlayerFragment()
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
}