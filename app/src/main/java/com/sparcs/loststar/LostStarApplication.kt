package com.sparcs.loststar

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.sparcs.loststar.util.Constants.KAKAO_NATIVE_KEY

class LostStarApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, KAKAO_NATIVE_KEY)
    }
}