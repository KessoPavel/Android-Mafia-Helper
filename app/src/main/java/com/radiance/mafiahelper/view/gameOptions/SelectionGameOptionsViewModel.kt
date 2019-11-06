package com.radiance.mafiahelper.view.gameOptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.GameOptions
import com.radiance.mafiahelper.tools.MutableLiveObject

class SelectionGameOptionsViewModel : ViewModel() {
    var gameOptions: MutableLiveObject<GameOptions> = MutableLiveObject()

    init {
        gameOptions.value = GameOptions()
    }


}
