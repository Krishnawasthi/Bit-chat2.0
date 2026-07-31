/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indianmesh.database.dao.MessageDao
import com.indianmesh.database.dao.UserDao
import com.indianmesh.database.entity.UserEntity
import com.indianmesh.network.MeshNetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val meshNetworkManager: MeshNetworkManager,
    private val userDao: UserDao,
    private val messageDao: MessageDao
) : ViewModel() {

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> = _users.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    init {
        // Start mesh network asynchronously
        meshNetworkManager.start()
        _isConnected.value = true
        
        // Start polling database for users (simulation of Flow)
        viewModelScope.launch {
            while (true) {
                fetchUsers()
                delay(2000)
            }
        }
    }

    private suspend fun fetchUsers() {
        withContext(Dispatchers.IO) {
            try {
                val allUsers = userDao.getAllUsers() // Stub list fetching safely
                _users.value = allUsers ?: emptyList()
            } catch (e: Exception) {
                // Ignore empty db exceptions for now
            }
        }
    }

    fun sendMessage(peerId: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            meshNetworkManager.sendPayload(text.toByteArray(), peerId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        meshNetworkManager.stop()
    }
}
