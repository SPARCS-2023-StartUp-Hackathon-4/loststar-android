package com.sparcs.loststar.ui.lostOrFindMore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityLoginBinding
import com.sparcs.loststar.databinding.ActivityLostOrFindMoreBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.LostFound
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.ui.home.adapter.LostOrFindRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostOrFindMoreActivity : AppCompatActivity() {

    private val binding: ActivityLostOrFindMoreBinding by lazy {
        ActivityLostOrFindMoreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        val rvMainAdapter = LostOrFindRecyclerViewAdapter()
        binding.rvMain.adapter = rvMainAdapter

        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().getList(
                type = LostFound.FOUND.name, location = "강남"
            ).onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    rvMainAdapter.submitList(data.content)
                }
            }
        }

        binding.ivFilter.setOnClickListener {
            binding.viewShadow.visibility = View.VISIBLE
            binding.bottomSheet.visibility = View.VISIBLE
        }

        binding.ivClose.setOnClickListener {
            dismissBottomSheet()
        }

    }

    private fun dismissBottomSheet() {
        binding.viewShadow.visibility = View.GONE
        binding.bottomSheet.visibility = View.GONE
    }
}