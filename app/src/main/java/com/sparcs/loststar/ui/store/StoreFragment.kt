package com.sparcs.loststar.ui.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityLostOrFindDetailBinding
import com.sparcs.loststar.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    private val binding: FragmentStoreBinding by lazy {
        FragmentStoreBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }
}