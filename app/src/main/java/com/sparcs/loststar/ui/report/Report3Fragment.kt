package com.sparcs.loststar.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReport3Binding
import com.sparcs.loststar.util.afterTextChanged

class Report3Fragment : Fragment() {

    private val binding: FragmentReport3Binding by lazy {
        FragmentReport3Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etStar.afterTextChanged { text ->
            (parentFragment as ReportFragment).star = text.toString()
            if(text.toString().isEmpty()) {
                binding.etStar.setBackgroundResource(R.drawable.bg_rectangle_empty_7e7e7e_radius_14)
                (parentFragment as ReportFragment).nextButton.text = "다음"
            } else {
                binding.etStar.setBackgroundResource(R.drawable.bg_rectangle_empty_white_radius_14)
                (parentFragment as ReportFragment).nextButton.text = "접수 완료!"
            }
            (parentFragment as ReportFragment).nextButton.isEnabled = text.toString().isNotEmpty()
        }
    }
}