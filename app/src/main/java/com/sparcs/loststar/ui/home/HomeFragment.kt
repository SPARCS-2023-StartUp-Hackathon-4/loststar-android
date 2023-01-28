package com.sparcs.loststar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentHomeBinding
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.ui.home.adapter.EmergencyRecyclerViewAdapter
import com.sparcs.loststar.ui.home.adapter.LostOrFindRecyclerViewAdapter
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

        binding.rvEmergency.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        val rvEmergencyAdapter = EmergencyRecyclerViewAdapter()
        binding.rvEmergency.adapter = rvEmergencyAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvEmergency)

        val list = listOf(
            TestEmergency(1,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(2,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(3,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(4,"","테스트",100,"2020","11:30","우리집"),
            TestEmergency(5,"","테스트",100,"2020","11:30","우리집")
        )

        rvEmergencyAdapter.submitList(list)

        binding.rvSub.layoutManager = LinearLayoutManager(requireContext())
        val rvSubAdapter = LostOrFindRecyclerViewAdapter()
        binding.rvSub.adapter = rvSubAdapter

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

        rvSubAdapter.submitList(list2)

        binding.stickNestedScrollView.run {
            header = binding.viewDivider
            stickListener = { _ ->
                setStatusBarColorGray()
                setBackgroundColorGray()
                changeLayoutColorDark()
            }
            freeListener = { _ ->
                setStatusBarColorBlack()
                setBackgroundColorBlack()
                changeLayoutColorLight()
            }
        }
    }

    private fun changeLayoutColorDark() {
        with(binding) {
            ivNoti.setImageResource(R.drawable.ic_noti_black)
            ivLocation.setImageResource(R.drawable.ic_location_black)
            tvLocation.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            rbFind.setBackgroundResource(R.drawable.selector_radio_button_lost_find_dark)
            rbFind.setTextColor(ContextCompat.getColorStateList(requireContext(),R.color.selector_radio_button_lost_find_text_dark))
            rbLost.setBackgroundResource(R.drawable.selector_radio_button_lost_find_dark)
            rbLost.setTextColor(ContextCompat.getColorStateList(requireContext(),R.color.selector_radio_button_lost_find_text_dark))
        }
    }

    private fun changeLayoutColorLight() {
        with(binding) {
            ivNoti.setImageResource(R.drawable.ic_noti_white)
            ivLocation.setImageResource(R.drawable.ic_location_white)
            tvLocation.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            rbFind.setBackgroundResource(R.drawable.selector_radio_button_lost_find_light)
            rbFind.setTextColor(ContextCompat.getColorStateList(requireContext(),R.color.selector_radio_button_lost_find_text_light))
            rbLost.setBackgroundResource(R.drawable.selector_radio_button_lost_find_light)
            rbLost.setTextColor(ContextCompat.getColorStateList(requireContext(),R.color.selector_radio_button_lost_find_text_light))
        }
    }

    private fun setStatusBarColorBlack() {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.black)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    }

    private fun setBackgroundColorBlack() {
        binding.root.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.black))
    }

    private fun setBackgroundColorGray() {
        binding.root.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.color_f9f9f9))
    }

    private fun setStatusBarColorGray() {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.color_f9f9f9)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }
}