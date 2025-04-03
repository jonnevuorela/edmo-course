package com.example.numberguessgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numberguessgame.models.NumberGuessState
import com.example.numberguessgame.vm.NumberGuessScreenViewModel

@Composable
fun NumberGuessScreenRoot(modifier: Modifier = Modifier) {
   val vm = viewModel<NumberGuessScreenViewModel>()
   val state by vm.state.collectAsStateWithLifecycle()

   NumberGuessScreen(state=state, onValueChange = {updatedText -> vm.updateNuberText(updatedText)}, onGuess = {vm.guess()})
}

@Composable
fun NumberGuessScreen(modifier: Modifier = Modifier, state: NumberGuessState, onValueChange: (String)->Unit,onGuess: () -> Unit) {
   Scaffold() {  paddingValues ->
      Column(modifier = Modifier.fillMaxSize().padding(paddingValues),
         verticalArrangement =  Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally) {
         TextField(
            value = state.number,
            onValueChange = { updatedText -> onValueChange(updatedText)
            })
         Text(state.guessText)
         TextButton(
            enabled = state.number != "",
            onClick = onGuess){
            Text("Arvaa")
         }
      }

   }
}