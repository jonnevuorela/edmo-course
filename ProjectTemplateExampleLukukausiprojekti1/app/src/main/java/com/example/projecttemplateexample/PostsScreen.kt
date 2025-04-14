package com.example.projecttemplateexample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projecttemplateexample.models.PostDto
import com.example.projecttemplateexample.models.PostState
import com.example.projecttemplateexample.models.UserState
import com.example.projecttemplateexample.vm.PostsViewModel

@Composable
fun PostsScreenRoot(modifier: Modifier = Modifier) {
    val vm = hiltViewModel<PostsViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()
    PostsScreen(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(modifier: Modifier = Modifier, state: PostState) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Posts")
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
                        Text(err)
                    }
                } ?: LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                    items(state.posts) { post ->
                        Text(post.title)
                    }
                }
            }
        }
    }
}

private fun LazyListScope.items(posts: PostDto, itemContent: @Composable() (LazyItemScope.(index: Int) -> Unit)) {
    TODO("Not yet implemented")
}
