package com.coding.baxta.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.coding.baxta.databinding.ActivityUserDetailsBinding
import com.coding.baxta.local.user.entity.User
import com.coding.baxta.local.user.entity.UserState
import com.coding.baxta.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsActivity : BaseActivity() {

    val mainActivityViewModel: MainActivityViewModel by viewModel()
    private lateinit var binder: ActivityUserDetailsBinding

    companion object {
        const val KEY_USERID = "user_id"

        fun <T : AppCompatActivity> startActivity(userId: String, activity: T) {
            val i = Intent(activity, UserDetailsActivity::class.java)
            i.putExtra(KEY_USERID, userId)
            ContextCompat.startActivity(activity, i, null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binder.root)
        initializeData()
        initializeInfo()
    }

    private fun initializeInfo() {
        val userId = intent.extras?.getString(KEY_USERID)
        mainActivityViewModel.watchUserInfo(userId ?: throw NullPointerException())
    }

    private fun initializeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mainActivityViewModel
                    .userState
                    .collect {
                        log("User Details: $it")
                        when (it) {
                            is UserState.GetUserInfoSuccess -> {
                                displayData(it.user)
                            }
                            is UserState.Error -> {
                                it.error?.message?.let { msg ->
                                    showToast(this@UserDetailsActivity, msg)
                                }
                            }
                        }
                    }
            }
        }
    }

    private fun displayData(user: User) {
        binder.fullName.text = user.fullName
        binder.age.text = user.getAge()
        binder.email.text = user.email
        binder.favorite.text = user.favoriteAnimal
        binder.userName.text = user.userName
        binder.followerCount.text = user.followersCount.toString()
    }
}