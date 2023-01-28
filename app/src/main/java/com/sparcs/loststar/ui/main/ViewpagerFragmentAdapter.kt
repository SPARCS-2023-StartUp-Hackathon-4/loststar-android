package com.sparcs.loststar.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sparcs.loststar.ui.chatting.chatroom.ChattingFragment
import com.sparcs.loststar.ui.home.HomeFragment
import com.sparcs.loststar.ui.profile.ProfileFragment
import com.sparcs.loststar.ui.report.ReportFragment
import com.sparcs.loststar.ui.store.StoreFragment

class ViewpagerFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    // 1. ViewPager2에 연결할 Fragment 들을 생성
    val fragmentList = listOf<Fragment>(HomeFragment(), ReportFragment(), StoreFragment(), ChattingFragment(), ProfileFragment())

    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // 3. ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}