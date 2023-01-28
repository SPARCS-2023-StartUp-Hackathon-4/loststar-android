package com.sparcs.loststar.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReport1Binding
import com.sparcs.loststar.util.afterTextChanged

class Report1Fragment : Fragment() {

    private val binding: FragmentReport1Binding by lazy {
        FragmentReport1Binding.inflate(layoutInflater)
    }

    private val editTextList: List<EditText> by lazy {
        listOf(
            binding.etName,
            binding.etCategory,
            binding.etRegion,
            binding.etRegionDetail,
            binding.etDate,
            binding.etTime
        )
    }

    private val textViewList: List<TextView> by lazy {
        listOf(
            binding.tvName,
            binding.tvCategory,
            binding.tvRegion,
            binding.tvRegionDetail,
            binding.tvDate,
            binding.tvTime
        )
    }

    private fun initEditTextLayoutSetting() {
        for(idx in editTextList.indices) {
            editTextList[idx].afterTextChanged { editable ->
                val text = editable.toString()
                if(text.isEmpty()) {
                    textViewList[idx].setTextColor(ContextCompat.getColor(requireContext(), R.color.color_7e7e7e))
                    editTextList[idx].setBackgroundResource(R.drawable.bg_rectangle_empty_7e7e7e_radius_14)
                } else {
                    textViewList[idx].setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    editTextList[idx].setBackgroundResource(R.drawable.bg_rectangle_empty_white_radius_14)
                }

                (parentFragment as ReportFragment).nextButton.isEnabled = idx == 5 && text.isNotEmpty()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditTextLayoutSetting()


    }
}