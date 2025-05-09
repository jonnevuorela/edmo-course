package com.example.projecttemplateexample.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttemplateexample.NetworkChecker
import com.example.projecttemplateexample.UserDataService
import com.example.projecttemplateexample.models.UserItemState
import com.example.projecttemplateexample.models.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val dataService: UserDataService, private val networkChecker: NetworkChecker) : ViewModel() {
    private val _state = MutableStateFlow(UserState())
    val state = _state.asStateFlow()

    private  val _userItem = MutableStateFlow(UserItemState())
    val userItem = _userItem.asStateFlow()

    init {
        getUsers()
    }

    fun getUserById(){
        viewModelScope.launch {
            if(networkChecker.isNetworkAvailable()){
                try{
                    _userItem.update { currentState ->
                        currentState.copy(loading = true, error = null)
                    }
                    val user = dataService.getUserById(1)
                    _userItem.update { currentState ->
                        currentState.copy(user=user)
                    }
                }catch (e: Exception){
                    _userItem.update { currentState ->
                        currentState.copy(error = e.toString())
                    }
                } finally {
                    _userItem.update { currentState ->
                        currentState.copy(loading = false)
                    }
                }
            }else{
                _userItem.update { currentState ->
                    currentState.copy( error = "No internet connection.")
                }

            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            if (networkChecker.isNetworkAvailable()){
                try {
                    _state.update { currentState ->
                        currentState.copy(loading = true, error = null)
                    }
                    val users = dataService.getUsers()
                    _state.update { currentState ->
                        currentState.copy(users=users)
                    }
                } catch(e: Exception) {
                    _state.update { currentState ->
                        currentState.copy(error = e.toString())
                    }
                } finally {
                    _state.update { currentState ->
                        currentState.copy(loading = false)
                    }
                }
            }else{
                _state.update { currentState ->
                    currentState.copy(error = "Network connection unavailable.")
                }
            }
        }
    }
}