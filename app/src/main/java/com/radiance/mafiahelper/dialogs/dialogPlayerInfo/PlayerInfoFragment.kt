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

    companion object {
        private const val PLAYER = "PLAYER"

        fun newInstance(player: Player): PlayerInfoFragment {
            val args = Bundle()
            args.putSerializable(PLAYER, player)
            val fragment = PlayerInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val detailsBinding =
            FragmentPlayerInfoBinding.inflate(inflater, container, false)
        player = arguments!!.getSerializable(PLAYER) as Player
        detailsBinding.player = player
        return detailsBinding.root
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return if (enter) {
            AnimationUtils.loadAnimation(context, R.anim.dialog_enter)
        } else {
            AnimationUtils.loadAnimation(context, R.anim.dialog_out)
        }
    }
}