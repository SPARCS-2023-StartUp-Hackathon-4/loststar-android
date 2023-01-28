package com.sparcs.loststar.ui.lostOrFindDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityLoginBinding
import com.sparcs.loststar.databinding.ActivityLostOrFindDetailBinding
import com.sparcs.loststar.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostOrFindDetailActivity : AppCompatActivity() {

    private val binding: ActivityLostOrFindDetailBinding by lazy {
        ActivityLostOrFindDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost_or_find_detail)
        val id = intent.getIntExtra("id", -1)
        Log.d("id", id.toString())
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().getLostOrFound(id).onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    Log.d("a", data.location)
                }
            }
        }
    }


}