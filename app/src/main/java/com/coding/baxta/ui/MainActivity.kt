package com.coding.baxta.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.baxta.databinding.ActivityMainBinding
import com.coding.baxta.local.user.entity.User
import com.coding.baxta.local.user.entity.UserState
import com.coding.baxta.ui.adapter.UserAdapter
import com.coding.baxta.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    val mainActivityViewModel: MainActivityViewModel by viewModel()
    private lateinit var binder: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        initializeRecyclerView()
        initializeData()
    }

    private fun initializeRecyclerView() {
        adapter = UserAdapter()
        val llm = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binder.recyclerView.layoutManager = llm
        binder.recyclerView.adapter = adapter
        adapter.clickListener = {
            UserDetailsActivity.startActivity(it.id, this)
        }
    }

    private fun feedData(users: List<User>) {
        adapter.submitList(users)
    }

    private fun initializeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mainActivityViewModel
                    .userState
                    .collect {
                        log("Value: $it")
                        when (it) {
                            is UserState.Error -> {
                                it.error?.message?.let { msg ->
                                    showToast(this@MainActivity, msg)
                                }
                            }
                            is UserState.GetUserListSuccess -> {
                                feedData(it.users)
                            }
                        }
                    }
            }
        }
    }


}