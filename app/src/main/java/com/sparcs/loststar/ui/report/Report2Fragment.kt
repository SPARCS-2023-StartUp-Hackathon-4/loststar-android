package com.sparcs.loststar.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.nguyenhoanglam.imagepicker.model.ImagePickerConfig
import com.nguyenhoanglam.imagepicker.ui.imagepicker.registerImagePicker
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReport2Binding
import com.sparcs.loststar.util.GlideUtil
import com.sparcs.loststar.util.afterTextChanged
import com.sparcs.loststar.util.px


class Report2Fragment : Fragment() {

    private val binding: FragmentReport2Binding by lazy {
        FragmentReport2Binding.inflate(layoutInflater)
    }

    private val launcher = registerImagePicker { images ->
        if(images.isNotEmpty()){
            val sampleImage = images[0]
            Glide.with(requireContext())
                .load(sampleImage.uri)
                .transform(CenterCrop(), RoundedCorners(20.px))
                .into(binding.ivSelected)
        }
    }

    val config = ImagePickerConfig(
        isShowCamera = true
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clAddImage.setOnClickListener {
            launcher.launch(config)
        }

        binding.etDetail.afterTextChanged { text ->
            if(text.toString().isEmpty()) {
                binding.etDetail.setBackgroundResource(R.drawable.bg_rectangle_empty_7e7e7e_radius_14)
            } else {
                binding.etDetail.setBackgroundResource(R.drawable.bg_rectangle_empty_white_radius_14)
            }
            (parentFragment as ReportFragment).nextButton.isEnabled = text.toString().isNotEmpty()
        }
    }
}