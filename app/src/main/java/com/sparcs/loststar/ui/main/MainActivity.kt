package com.sparcs.loststar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.card.MaterialCardView
import com.google.firebase.messaging.FirebaseMessaging
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityMainBinding
import com.sparcs.loststar.util.PreferenceUtil
import com.sparcs.loststar.util.PreferenceUtil.Companion.prefs


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val bottomCardList: List<MaterialCardView> by lazy {
        listOf(
            binding.cardViewHome,
            binding.cardViewReport,
            binding.cardViewStore,
            binding.cardViewChatting,
            binding.cardViewProfile
        )
    }

    private val bottomIconList: List<ImageView> by lazy {
        listOf(
            binding.ivHome,
            binding.ivReport,
            binding.ivStore,
            binding.ivChatting,
            binding.ivProfile
        )
    }

    private val bottomTextViewList: List<TextView> by lazy {
        listOf(
            binding.tvHome,
            binding.tvReport,
            binding.tvStore,
            binding.tvChatting,
            binding.tvProfile
        )
    }

    private val viewpagerFragmentAdapter: ViewpagerFragmentAdapter by lazy {
        ViewpagerFragmentAdapter(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBarColorBlack()

        with(binding) {
            vp.adapter = viewpagerFragmentAdapter
            vp.isUserInputEnabled = false

            cardViewHome.setOnClickListener {
                setStatusBarColorBlack()
                vp.setCurrentItem(0, false)
                initBottomNavi()
                cardViewHome.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.color_f9f9f9
                    )
                )
                ivHome.setImageResource(R.drawable.ic_menu_home_active)
                tvHome.typeface =
                    ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewReport.setOnClickListener {
                vp.setCurrentItem(1, false)
                initBottomNavi()
                cardViewReport.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.color_f9f9f9
                    )
                )
                ivReport.setImageResource(R.drawable.ic_menu_report_active)
                tvReport.typeface =
                    ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewStore.setOnClickListener {
                vp.setCurrentItem(2, false)
                initBottomNavi()
                cardViewStore.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.color_f9f9f9
                    )
                )
                ivStore.setImageResource(R.drawable.ic_menu_store_active)
                tvStore.typeface =
                    ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewChatting.setOnClickListener {
                vp.setCurrentItem(3, false)
                initBottomNavi()
                cardViewChatting.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.color_f9f9f9
                    )
                )
                ivChatting.setImageResource(R.drawable.ic_menu_store_active)
                tvChatting.typeface =
                    ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
            }

            cardViewProfile.setOnClickListener {
                vp.setCurrentItem(4, false)
                initBottomNavi()
                cardViewProfile.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.color_f9f9f9
                    )
                )
                ivProfile.setImageResource(R.drawable.ic_menu_profile_active)
                tvProfile.typeface =
                    ResourcesCompat.getFont(this@MainActivity, R.font.pre_semibold_600)
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
        bottomIconList[3].setImageResource(R.drawable.ic_menu_store_inactive)
        bottomIconList[4].setImageResource(R.drawable.ic_menu_profile_inactive)

        bottomTextViewList.forEach { textView ->
            textView.typeface = ResourcesCompat.getFont(this, R.font.pre_medium_500)
        }
    }

    private fun setStatusBarColorBlack() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color_1c1c1e)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    }

    private fun setStatusBarColorGray() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color_f9f9f9)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }
}