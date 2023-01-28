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
    private var currentPage = 0

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

        binding.buttonNext.setOnClickListener {
            binding.vp.setCurrentItem(++currentPage, true)
            binding.buttonNext.isEnabled = false
            if(currentPage == 1) binding.progress2.visibility = View.VISIBLE
            if(currentPage == 2) binding.progress3.visibility = View.VISIBLE
        }
    }
}