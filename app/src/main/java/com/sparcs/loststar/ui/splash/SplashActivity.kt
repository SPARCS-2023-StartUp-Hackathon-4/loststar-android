package com.sparcs.loststar.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.LostStarApplication
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivitySplashBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.ui.login.LoginActivity
import com.sparcs.loststar.ui.main.MainActivity
import com.sparcs.loststar.util.PreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().fetchMyInfo().onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }.onFailure {
                LostStarApplication.encryptedPrefs.saveAccessToken("")
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }


    }
}