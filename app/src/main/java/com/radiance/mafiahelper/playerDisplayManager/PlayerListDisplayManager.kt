package com.radiance.mafiahelper.playerDisplayManager

import android.graphics.drawable.Drawable
import com.radiance.mafiahelper.player.Player

class PlayerListDisplayManager(
    player: Player,
    val inactiveBackground: Drawable,
    val activeBackground: Drawable,
    val inactiveIcon: Int,
    val activeIcon: Int
    ): BaseDisplayManager(player) {

    var isSelected: Boolean = false

    data class Builder(
        var player: Player? = null,
        var inactiveBackground: Drawable? = null,
        var activeBackground: Drawable? = null,
        var inactiveIcon: Int? = null,
        var activeIcon: Int? = null) {

        fun player(player: Player) = apply { this.player = player }
        fun inactiveBackground(inactiveBackground: Drawable) = apply { this.inactiveBackground = inactiveBackground }
        fun activeBackground(activeBackground: Drawable) = apply { this.activeBackground = activeBackground }
        fun inactiveIcon(inactiveIcon: Int) = apply { this.inactiveIcon = inactiveIcon }
        fun activeIcon(activeIcon: Int) = apply { this.activeIcon = activeIcon }
        fun build(): PlayerListDisplayManager =
            PlayerListDisplayManager(player!!, inactiveBackground!!, activeBackground!!, inactiveIcon!!, activeIcon!!)
    }
}