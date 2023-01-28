package com.sparcs.loststar.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReport1Binding

class Report1Fragment : Fragment() {

    private val binding: FragmentReport1Binding by lazy {
        FragmentReport1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as ReportFragment).nextButton.isEnabled = false
    }
}