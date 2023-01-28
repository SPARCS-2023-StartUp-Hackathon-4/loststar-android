package com.sparcs.loststar.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.LostStarApplication
import com.sparcs.loststar.databinding.ActivityLoginBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.KakaoLoginRequest
import com.sparcs.loststar.ui.main.MainActivity
import com.sparcs.loststar.util.GlideUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private val TAG = "카카오"

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var kakaoToken = ""
    private var fcmToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        Log.d("kakao","키 해시 값 : $keyHash")


        // 연결 끊기
//        UserApiClient.instance.unlink { error ->
//            if (error != null) {
//                Log.e(TAG, "연결 끊기 실패", error)
//            }
//            else {
//                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
//            }
//        }

        binding.btnKakao.setOnClickListener {
            FirebaseMessaging.getInstance().token.addOnSuccessListener {
                fcmToken = it
                kakaoLoginAndGetProfileUrl {
                    doWhenLoginSuccess()
                }
            }

        }
    }

    // 로그인 성공 시 수행할 동작
    private fun doWhenLoginSuccess() {
        getUserProfileUrl { url ->
            GlideUtil.loadImage(binding.ivUserProfile, url)
            CoroutineScope(IO).launch {
                RetrofitClient.getApiService().kakaoLogin(KakaoLoginRequest(kakaoToken, "강남", url, fcmToken)).onSuccess {
                    Log.d("테스트", "통신 성공")
                    val acToken = data.accessToken
                    CoroutineScope(Main).launch {
                        LostStarApplication.encryptedPrefs.saveAccessToken(acToken)

                        withContext(IO) {
                            RetrofitClient.getApiService().fetchMyInfo().onSuccess {
                                LostStarApplication.prefs.setString("userId", data.id.toString())
                                val a = LostStarApplication.prefs.getString("userId", "")
                                Log.d("테스트","저장된 userId $a")

                                CoroutineScope(Main).launch {
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getUserProfileUrl(onSuccess: (url: String) -> Unit) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                val url = user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""
                onSuccess(url)
            }
        }
    }

    private fun kakaoLoginAndGetProfileUrl(onSuccess: () -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    kakaoToken = token.accessToken
                    onSuccess()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.d(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            kakaoToken = token.accessToken
            //TODO 로그인 성공 시 수행할 것
            doWhenLoginSuccess()
        }
    }
}