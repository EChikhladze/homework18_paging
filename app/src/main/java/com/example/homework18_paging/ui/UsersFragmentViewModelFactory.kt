package com.example.homework18_paging.ui

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.homework18_paging.paging.UserRepository

class UsersFragmentViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: UserRepository
) :
    AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(UsersFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}