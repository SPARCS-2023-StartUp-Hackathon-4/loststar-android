package com.sparcs.loststar.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReportBinding
import com.sparcs.loststar.ui.main.ViewpagerFragmentAdapter

class ReportFragment : Fragment() {

    private val binding: FragmentReportBinding by lazy {
        FragmentReportBinding.inflate(layoutInflater)
    }

    val nextButton: AppCompatButton by lazy {
        binding.buttonNext
    }

    private val viewpagerFragmentAdapter: ReportViewpagerFragmentAdapter by lazy {
        ReportViewpagerFragmentAdapter(
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vp.adapter = viewpagerFragmentAdapter
        binding.vp.isUserInputEnabled = false
    }
}