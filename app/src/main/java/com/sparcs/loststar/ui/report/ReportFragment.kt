package com.sparcs.loststar.ui.report

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.FragmentReportBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.LostFoundRequest
import com.sparcs.loststar.ui.main.ViewpagerFragmentAdapter
import com.sparcs.loststar.ui.reportComplete.ReportCompleteActivity
import com.sparcs.loststar.util.FormDataUtil
import com.sparcs.loststar.util.ImageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.io.File

class ReportFragment : Fragment() {

    private val binding: FragmentReportBinding by lazy {
        FragmentReportBinding.inflate(layoutInflater)
    }

    val nextButton: AppCompatButton by lazy {
        binding.buttonNext
    }
    private var currentPage = 0

    var name: String = ""
    var category: String = ""
    var region: String = ""
    var regionDetail: String = ""
    var date: String = ""
    var time: String = ""
    var imgUri: Uri? = null
    var description: String = ""
    var star: String = ""

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
        binding.vp.isSaveEnabled = false

        binding.buttonNext.setOnClickListener {
            if (currentPage == 2) {
                CoroutineScope(IO).launch {
                    RetrofitClient.getApiService().uploadImage(
                        FormDataUtil.getImageBody(
                            "image",
                            File(ImageUtil(requireContext()).getRealPathFromURI(imgUri!!))
                        )
                    ).onSuccess {
                        val image = data.image
                        CoroutineScope(IO).launch {
                            RetrofitClient.getApiService().postLostOrFound(
                                LostFoundRequest(
                                    type = "LOST",
                                    title = name,
                                    category = category,
                                    location = region,
                                    locationDetail = regionDetail,
                                    date = date,
                                    time = time,
                                    image = image,
                                    description = description,
                                    reward = star.toInt(),
                                    boost = false
                                )
                            ).onSuccess {
                                CoroutineScope(Main).launch {
                                    val intent = Intent(requireContext(), ReportCompleteActivity::class.java)
                                    intent.putExtra("reportId",data.id.toInt())
                                    startActivity(intent)
                                    requireActivity().finish()
                                }
                            }
                        }
                    }
                }
            }

            binding.vp.setCurrentItem(++currentPage, true)
            binding.buttonNext.isEnabled = false
            if (currentPage == 1) binding.progress2.visibility = View.VISIBLE
            if (currentPage == 2) binding.progress3.visibility = View.VISIBLE
        }
    }
}