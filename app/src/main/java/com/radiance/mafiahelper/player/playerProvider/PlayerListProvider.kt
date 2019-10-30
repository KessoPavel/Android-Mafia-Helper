package com.radiance.mafiahelper.player.playerProvider

import android.graphics.drawable.Drawable

interface PlayerListProvider: PlayerProvider {
    val inactiveBackground: Drawable?
    val activeBackground: Drawable?
    val inactiveIcon: Int
    val activeIcon: Int
    var isSelected: Boolean
}