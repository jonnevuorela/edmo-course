package com.example.projecttemplateexample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projecttemplateexample.models.UserItemState
import com.example.projecttemplateexample.models.UserState
import com.example.projecttemplateexample.vm.UsersViewModel

@Composable
fun UserScreenRoot(modifier: Modifier = Modifier, vm: UsersViewModel) {
    LaunchedEffect(Unit) {
        vm.getUserById()
    }
    val userState by vm.userItem.collectAsStateWithLifecycle()
    UserScreen(state = userState)

}

@Composable
fun UserScreen(modifier: Modifier = Modifier, state: UserItemState) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(state.user?.name ?: "User not found")
    }

}
