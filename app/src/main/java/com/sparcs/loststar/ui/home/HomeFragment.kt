package com.sparcs.loststar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentHomeBinding
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.ui.home.adapter.EmergencyRecyclerViewAdapter
import com.sparcs.loststar.util.CenterZoomLinearLayoutManager

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMain.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        val rvAdapter = EmergencyRecyclerViewAdapter()
        binding.rvMain.adapter = rvAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvMain)

        val list = listOf(
            TestEmergency(1,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(2,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(3,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(4,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(5,"","테스트",100,"2020","11:30","우리집")
        )

        rvAdapter.submitList(list)
    }
}