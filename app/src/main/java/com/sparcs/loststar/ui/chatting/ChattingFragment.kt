package com.sparcs.loststar.ui.chatting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentChattingBinding

class ChattingFragment : Fragment() {

    private val binding: FragmentChattingBinding by lazy {
        FragmentChattingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO 여기서 작업 ㄲㄲㄲㄲㄲ
    }
}