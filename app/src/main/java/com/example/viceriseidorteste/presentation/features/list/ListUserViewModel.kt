package com.example.viceriseidorteste.presentation.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viceriseidorteste.domain.UserRepository
import com.example.viceriseidorteste.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
): ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var currentPage = 1
    private val pageSize = 20
    private var endReach = false

    val filteredUsers: StateFlow<List<User>> = combine(_users, _searchQuery) { users, query ->
        if (query.isBlank()) users
        else users.filter { it.name.contains(query, ignoreCase = true) || it.email.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        fetchUsers()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun reloadUsers() {
        currentPage = 1
        endReach = false
        _users.value = emptyList()
        fetchUsers()
    }


    fun fetchUsers() {
        print("dentro do fetch: ${_isRefreshing.value} \n")
        if(_isRefreshing.value || endReach) return
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                val users = userRepository.getUsers(page = currentPage, pageSize = pageSize)
                if(users.isEmpty()) {
                    endReach = true
                } else {
                    _users.value += users
                    currentPage++
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao carregar usuários"
            } finally {
                _isRefreshing.value= false
            }
        }
    }
}