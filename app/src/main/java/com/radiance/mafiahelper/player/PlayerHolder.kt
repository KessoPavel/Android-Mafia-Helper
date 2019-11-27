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

    // new
    val icon: Int = 0,
    var isSelected: Boolean = false,
    var titleColor: Int = 0,
    var title: String = ""
)  {
}