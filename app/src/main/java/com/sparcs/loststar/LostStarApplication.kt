package com.sparcs.loststar

import android.app.Application
import android.app.Dialog
import com.kakao.sdk.common.KakaoSdk
import com.sparcs.loststar.util.Constants.KAKAO_NATIVE_KEY
import com.sparcs.loststar.util.EncryptedPrefsManger
import com.sparcs.loststar.util.PrefsManager

class LostStarApplication : Application() {

    companion object {
        lateinit var prefs: PrefsManager
        lateinit var encryptedPrefs: EncryptedPrefsManger
        lateinit var instance: LostStarApplication
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        encryptedPrefs = EncryptedPrefsManger(applicationContext)
        instance = this
        super.onCreate()

        KakaoSdk.init(this, KAKAO_NATIVE_KEY)
    }
}