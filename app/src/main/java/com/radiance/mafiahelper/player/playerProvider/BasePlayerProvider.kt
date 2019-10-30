package com.radiance.mafiahelper.player.playerProvider

import android.graphics.drawable.Drawable
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

class BasePlayerProvider(
    val player: Player,
    var isClickable: Boolean = false,
    val number: String = "",
    val status: String = "",
    var isRed: Boolean = player.role == Role.Red,
    var isBlack: Boolean = player.role == Role.Black,
    var isDoctor: Boolean = player.role == Role.Doctor,
    var isSheriff: Boolean = player.role == Role.Sheriff,
    val name: String = player.name,
    val statistic: String = "${player.gameCount} / ${player.victoriesCount}",
    val pseudonym: String = player.pseudonym,
    val inactiveBackground: Drawable? = null,
    val activeBackground: Drawable? = null,
    val inactiveIcon: Int = 0,
    val activeIcon: Int = 0,
    var isSelected: Boolean = false,
    val votingCount: String = ""
)  {
}