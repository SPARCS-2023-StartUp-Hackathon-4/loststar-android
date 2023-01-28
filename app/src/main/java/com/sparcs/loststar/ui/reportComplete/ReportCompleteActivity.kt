package com.sparcs.loststar.ui.reportComplete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityReportCompleteBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.UseSpeakerRequest
import com.sparcs.loststar.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ReportCompleteActivity : AppCompatActivity() {

    private val binding: ActivityReportCompleteBinding by lazy {
        ActivityReportCompleteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color_1c1c1e)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        val reportId = intent.getIntExtra("reportId", -1)

        binding.buttonHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnHuble.setOnClickListener {
            CoroutineScope(IO).launch {
                RetrofitClient.getApiService().notifyAll(UseSpeakerRequest(reportId.toLong())).onSuccess {
                    CoroutineScope(Main).launch {
                        binding.tvHubleCount.text = "0개 보유중"
                        Toast.makeText(applicationContext, "허블 확성기 사용!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}