package com.example.numberguessgame.vm

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import com.example.numberguessgame.models.NumberGuessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class NumberGuessScreenViewModel : ViewModel(){
    private val _state = MutableStateFlow(NumberGuessState())
    val state = _state.asStateFlow()
    private var randomNumber: Int? = null

    init {
        randomNumber = Random.nextInt(1, 101)

    }

    fun updateNuberText(updatedText: String){
        _state.update { currentState ->
            currentState.copy(number = updatedText)
        }
    }
    fun guess(){
        val numberInt = _state.value.number.toIntOrNull() ?: 0
        randomNumber?.let { rNumber ->
            var text ="Arvasit oikein"
            if (numberInt > rNumber){
                text = "Arvasit väärin, arvauksesi on suurempi"
            }else if (numberInt < rNumber){
                text = "Arvasit väärin arvauksesi on pienempi"
            }
            _state.update { currentState ->
                currentState.copy(guessText = text)
            }
        }
    }

}