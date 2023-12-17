package com.example.homework18_paging.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework18_paging.BaseFragment
import com.example.homework18_paging.api.Network
import com.example.homework18_paging.databinding.FragmentUsersBinding
import com.example.homework18_paging.paging.UserRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {
    private val viewModel: UsersFragmentViewModel by viewModels {
        UsersFragmentViewModelFactory(
            this,
            UserRepository(Network.getUsersService())
        )
    }
    private val usersAdapter by lazy { UserAdapter() }

    override fun setUp() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        with(binding.recyclerUsers) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = usersAdapter
        }
    }

    override fun listeners() {}

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersFlow.collectLatest(usersAdapter::submitData)
            }
        }
    }
}