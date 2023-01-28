package com.sparcs.loststar.ui.reportComplete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityReportCompleteBinding
import com.sparcs.loststar.ui.main.MainActivity

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

        binding.buttonHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}