package com.example.projecttemplateexample.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttemplateexample.PostDataService
import com.example.projecttemplateexample.models.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val dataService: PostDataService) : ViewModel() {
    private val _state = MutableStateFlow(PostState())
    val state = _state.asStateFlow()

    init {
       getPosts()
    }

    private fun getPosts(){
        viewModelScope.launch {
            try {
                _state.update { currentState ->
                    currentState.copy( loading = true, error = null)
                }
                // kutsu rajapintaan josta tulee postaukset
                val posts = dataService.getPosts()
                _state.update { currentState ->
                   currentState.copy(posts=posts)
                }

            }catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            }finally {
                _state.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }
}
