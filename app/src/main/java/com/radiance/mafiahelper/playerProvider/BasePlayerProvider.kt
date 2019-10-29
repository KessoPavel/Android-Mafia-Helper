package com.radiance.mafiahelper.playerProvider

import android.graphics.drawable.Drawable
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

class BasePlayerProvider(
    override val player: Player,
    override val isClickable: Boolean = false,
    override val number: String = "",
    override val isRed: Boolean = player.role == Role.Red,
    override val isBlack: Boolean = player.role == Role.Black,
    override val isDoctor: Boolean = player.role == Role.Doctor,
    override val isSheriff: Boolean = player.role == Role.Sheriff,
    override val name: String = player.name,
    override val statistic: String = "${player.gameCount} / ${player.victoriesCount}",
    override val pseudonym: String = player.pseudonym,
    override val inactiveBackground: Drawable? = null,
    override val activeBackground: Drawable? = null,
    override val inactiveIcon: Int = 0,
    override val activeIcon: Int = 0,
    override var isSelected: Boolean = false,
    override val votingCount: String = ""
) : DayProvider,
    FirstNightProvider,
    GettingToKnownProvider,
    PlayerListProvider,
    VotingProvider {
}