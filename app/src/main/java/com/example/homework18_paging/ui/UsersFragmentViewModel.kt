package com.example.homework18_paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework18_paging.common.User
import com.example.homework18_paging.paging.UserRepository
import kotlinx.coroutines.flow.Flow

class UsersFragmentViewModel(repository: UserRepository) : ViewModel() {

    val usersFlow: Flow<PagingData<User>> = repository.getUsersFlow().cachedIn(viewModelScope)

//    private fun getUsers(): Flow<PagingData<User>> = repository.getUsersFlow()
}