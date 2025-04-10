package com.example.projecttemplateexample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projecttemplateexample.models.UserState
import com.example.projecttemplateexample.vm.UsersViewModel

@Composable
fun UsersScreenRoot(
    modifier: Modifier = Modifier,
    vm: UsersViewModel,
    onNavigate: () -> Unit,
    onMenuClick: () -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()
    UsersScreen(state = state, onNavigate = onNavigate, onRetry = {
        vm.getUsers()
    }, onMenuClick ={} )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    state: UserState,
    onNavigate: () -> Unit,
    onRetry: () -> Unit,
    onMenuClick : () -> Unit
    ) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },title = {
            Text("Users")
        })
    }) { paddingValues ->
        when {
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                state.error?.let { err ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(err)
                            Button(onClick = {
                                onRetry()
                            }) {
                                Text("Retry")
                            }
                        }
                    }
                } ?: LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                    items(state.users) { user ->
                        Text(user.name, modifier = Modifier.clickable {
                            onNavigate()
                        })
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}