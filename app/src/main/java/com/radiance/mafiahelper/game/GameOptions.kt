package com.radiance.mafiahelper.game

import androidx.databinding.BaseObservable
class GameOptions: BaseObservable() {
    var playersCount: Int = 0
        set(value) {
            field = value
            notifyChange()
        }

    var blackCount: Int = 0
        set(value) {
            field = value
            notifyChange()
        }

    var doctorInGame: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }

    var sheriffInGame: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }
}