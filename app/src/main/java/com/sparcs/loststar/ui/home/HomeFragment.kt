package com.sparcs.loststar.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentHomeBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.Location
import com.sparcs.loststar.network.model.LostFound
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.ui.chatting.chat.ChatInsideActivity
import com.sparcs.loststar.ui.chatting.chatroom.ChatRoomListRVAdapter
import com.sparcs.loststar.ui.home.adapter.EmergencyRecyclerViewAdapter
import com.sparcs.loststar.ui.home.adapter.LostOrFindRecyclerViewAdapter
import com.sparcs.loststar.ui.lostOrFindDetail.LostOrFindDetailActivity
import com.sparcs.loststar.ui.lostOrFindMore.LostOrFindMoreActivity
import com.sparcs.loststar.util.CenterZoomLinearLayoutManager
import com.sparcs.loststar.util.PreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val lostOrFoundIdList: List<Long> = mutableListOf()

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

        if (binding.rvEmergency.onFlingListener == null) {
            snapHelper.attachToRecyclerView(binding.rvEmergency)
        }

        // userid ??????
        CoroutineScope(IO).launch {
            RetrofitClient.getApiService().fetchMyInfo().onSuccess {
                CoroutineScope(Main).launch {
                    Log.d("userid init", data.id.toString())
                    PreferenceUtil.prefs.setString("userid", data.id.toString())
                }
            }
        }


        binding.rvSub.layoutManager = LinearLayoutManager(requireContext())
        val rvSubAdapter = LostOrFindRecyclerViewAdapter()
        binding.rvSub.adapter = rvSubAdapter

        // ??? ??? ?????? ????????? ?????? ??? ????????????
        fetchEmergencyList(LostFound.LOST, rvEmergencyAdapter)
        fetchList(Location.??????.name, LostFound.LOST, rvSubAdapter)

        // ????????? ??????, ??????
        binding.rbFind.setOnClickListener {
            fetchEmergencyList(LostFound.FOUND, rvEmergencyAdapter)
            fetchList(Location.??????.name, LostFound.FOUND, rvSubAdapter)
        }

        // ????????? ??????, ??????
        binding.rbLost.setOnClickListener {
            fetchEmergencyList(LostFound.LOST, rvEmergencyAdapter)
            fetchList(Location.??????.name, LostFound.LOST, rvSubAdapter)
        }


        binding.stickNestedScrollView.run {
            header = binding.viewDivider
            stickListener = { _ ->
                setStatusBarColorGray()
                setBackgroundColorGray()
                changeLayoutColorDark()
                binding.ivLogo.setImageResource(R.drawable.ic_logo_wide_black)
            }
            freeListener = { _ ->
                setStatusBarColorBlack()
                setBackgroundColorBlack()
                changeLayoutColorLight()
                binding.ivLogo.setImageResource(R.drawable.ic_logo_wide_white)
            }
        }

        binding.btnMore.setOnClickListener {
            startActivity(Intent(requireContext(), LostOrFindMoreActivity::class.java))
        }

        // sub ?????? ?????? -> ?????? ?????? ??????
        rvSubAdapter.itemClick = object : LostOrFindRecyclerViewAdapter.ItemClick {
            override fun onClick(id: Int) {
                val intent = Intent( binding.rvSub.context, LostOrFindDetailActivity::class.java)
                intent.putExtra("id", id)
                context?.startActivity(intent)
            }
        }

        // emergency ?????? ?????? -> ?????? ?????? ??????
        rvEmergencyAdapter.itemClick = object : EmergencyRecyclerViewAdapter.ItemClick {
            override fun onClick(id: Int) {
                val intent = Intent( binding.rvEmergency.context, LostOrFindDetailActivity::class.java)
                intent.putExtra("id", id)
                context?.startActivity(intent)
            }
        }
    }

    private fun fetchList(location: String, type: LostFound, rvSubAdapter: LostOrFindRecyclerViewAdapter) {
        CoroutineScope(IO).launch {
            RetrofitClient.getApiService().getList(
                type = type.name, location = location
            ).onSuccess {
                CoroutineScope(Main).launch {
                    rvSubAdapter.submitList(data.content)
                }
            }
        }
    }

    private fun fetchEmergencyList(type: LostFound, rvEmergencyAdapter: EmergencyRecyclerViewAdapter) {
        CoroutineScope(IO).launch {
            RetrofitClient.getApiService().getBoosts(type.name).onSuccess {
                CoroutineScope(Main).launch {
                    rvEmergencyAdapter.submitList(data.list)
                }
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