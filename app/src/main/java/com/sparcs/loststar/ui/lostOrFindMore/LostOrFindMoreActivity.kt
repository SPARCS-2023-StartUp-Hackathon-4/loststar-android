package com.sparcs.loststar.ui.lostOrFindMore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityLoginBinding
import com.sparcs.loststar.databinding.ActivityLostOrFindMoreBinding
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.ui.home.adapter.LostOrFindRecyclerViewAdapter

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

        val list2 = listOf(
            TestLostOrFind(1,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(2,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(3,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(4,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(5,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(6,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(7,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(8,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(9,"","테스트",100,"2020","11:30","우리집"),
            TestLostOrFind(10,"","테스트",100,"2020","11:30","우리집"),
        )

        rvMainAdapter.submitList(list2)

    }
}