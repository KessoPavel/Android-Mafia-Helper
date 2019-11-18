package com.radiance.mafiahelper.player

import android.graphics.drawable.Drawable

class PlayerHolder(
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

    val inVoting: Boolean = false,

    // old
    val inactiveBackground: Drawable? = null,
    val activeBackground: Drawable? = null,
    val inactiveIcon: Int = 0,
    val activeIcon: Int = 0,
    // new
    val background: Drawable? = null,
    val icon: Int = 0,
    var isSelected: Boolean = false,
    val votingCount: String = ""
)  {
}