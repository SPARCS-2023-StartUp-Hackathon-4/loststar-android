package com.sparcs.loststar.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.card.MaterialCardView
import com.google.firebase.messaging.FirebaseMessaging
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val bottomCardList: List<MaterialCardView> by lazy {
        listOf(
            binding.cardViewHome,
            binding.cardViewReport,
            binding.cardViewStore,
            binding.cardViewProfile
        )
    }

    private val bottomIconList: List<ImageView> by lazy {
        listOf(
            binding.ivHome,
            binding.ivReport,
            binding.ivStore,
            binding.ivProfile
        )
    }

    private val bottomTextViewList: List<TextView> by lazy {
        listOf(
            binding.tvHome,
            binding.tvReport,
            binding.tvStore,
            binding.tvProfile
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("Firebase", "$it")
        }

        with(binding) {
            cardViewHome.setOnClickListener {
                initBottomNavi()
                cardViewHome.setCardBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_f9f9f9))
                ivHome.setImageResource(R.drawable.ic_menu_home_active)
                tvHome.typeface = ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewReport.setOnClickListener {
                initBottomNavi()
                cardViewReport.setCardBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_f9f9f9))
                ivReport.setImageResource(R.drawable.ic_menu_report_active)
                tvReport.typeface = ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewStore.setOnClickListener {
                initBottomNavi()
                cardViewStore.setCardBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_f9f9f9))
                ivStore.setImageResource(R.drawable.ic_menu_store_active)
                tvStore.typeface = ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewProfile.setOnClickListener {
                initBottomNavi()
                cardViewProfile.setCardBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_f9f9f9))
                ivProfile.setImageResource(R.drawable.ic_menu_profile_active)
                tvProfile.typeface = ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }
        }
    }

    private fun initBottomNavi() {
        bottomCardList.forEach { card ->
            card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }

        bottomIconList[0].setImageResource(R.drawable.ic_menu_home_inactive)
        bottomIconList[1].setImageResource(R.drawable.ic_menu_report_inactive)
        bottomIconList[2].setImageResource(R.drawable.ic_menu_store_inactive)
        bottomIconList[3].setImageResource(R.drawable.ic_menu_profile_inactive)

        bottomTextViewList.forEach { textView ->
            textView.typeface = ResourcesCompat.getFont(this, R.font.pre_medium_500)
        }
    }
}