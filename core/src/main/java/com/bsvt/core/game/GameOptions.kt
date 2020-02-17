package com.bsvt.core.game

import androidx.databinding.BaseObservable
import java.io.Serializable

class GameOptions: BaseObservable(), Serializable {
    var playersCount: Int = 0
        set(value) {
            field = value
            notifyChange()
        }
    var blackCount: Int = 1
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

    var redCount: Int = 0
        get() {
            field = playersCount - (blackCount + (if (doctorInGame) 1 else 0) + (if (sheriffInGame) 1 else 0))
            return field
        }

}