package com.radiance.mafiahelper.playerProvider

import android.graphics.drawable.Drawable
import com.radiance.mafiahelper.player.Player

class BasePlayerProvider(
    override val player: Player,
    override val isClickable: Boolean = false,
    override val number: String = "",
    override val isRed: Boolean = false,
    override val isBlack: Boolean = false,
    override val isDoctor: Boolean = false,
    override val isSheriff: Boolean = false,
    override val name: String = "",
    override val statistic: String = "",
    override val pseudonym: String = "",
    override val inactiveBackground: Drawable? = null,
    override val activeBackground: Drawable? = null,
    override val inactiveIcon: Int = 0,
    override val activeIcon: Int = 0,
    override val votingCount: String = ""
) : DayProvider,
    FirstNightProvider,
    GettingToKnownProvider,
    PlayerListProvider,
    VotingProvider {
}