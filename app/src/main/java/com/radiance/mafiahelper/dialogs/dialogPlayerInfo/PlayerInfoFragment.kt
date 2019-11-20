package com.radiance.mafiahelper.dialogs.dialogPlayerInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.databinding.FragmentPlayerInfoBinding
import com.radiance.mafiahelper.player.Player

class PlayerInfoFragment: Fragment() {
    private lateinit var player: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val detailsBinding =
            FragmentPlayerInfoBinding.inflate(inflater, container, false)
        arguments?.let {
            val args = PlayerInfoFragmentArgs.fromBundle(it)
            player = args.player
        }

        detailsBinding.player = player
        return detailsBinding.root
    }
}