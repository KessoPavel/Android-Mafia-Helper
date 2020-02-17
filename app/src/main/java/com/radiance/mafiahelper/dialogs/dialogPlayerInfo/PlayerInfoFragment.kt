package com.radiance.mafiahelper.dialogs.dialogPlayerInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bsvt.core.player.Player
import com.radiance.mafiahelper.databinding.FragmentPlayerInfoBinding

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
            player = it.getSerializable(playerKey) as Player
        }

        detailsBinding.player = player
        return detailsBinding.root
    }

    companion object {
        fun createFragment(player: Player): PlayerInfoFragment {
            val fragment = PlayerInfoFragment()

            val bundle = Bundle()
            bundle.putSerializable(playerKey, player)

            fragment.arguments = bundle
            return fragment
        }

        private const val playerKey = "player"
    }
}