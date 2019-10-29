package com.radiance.mafiahelper.playerProvider

import android.graphics.drawable.Drawable

interface PlayerListProvider: PlayerProvider {
    val inactiveBackground: Drawable?
    val activeBackground: Drawable?
    val inactiveIcon: Int
    val activeIcon: Int
    var isSelected: Boolean
}