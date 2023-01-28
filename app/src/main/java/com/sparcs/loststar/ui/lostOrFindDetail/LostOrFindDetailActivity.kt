package com.sparcs.loststar.ui.lostOrFindDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.R
import com.sparcs.loststar.databinding.ActivityLoginBinding
import com.sparcs.loststar.databinding.ActivityLostOrFindDetailBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.ui.chatting.chatroom.ChatRoomInfoModel
import com.sparcs.loststar.util.FBRef
import com.sparcs.loststar.util.PreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostOrFindDetailActivity : AppCompatActivity() {

    private val binding: ActivityLostOrFindDetailBinding by lazy {
        ActivityLostOrFindDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra("id", -1)
        Log.d("id", id.toString())
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().getLostOrFound(id).onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    Log.d("a", data.location)
                }
            }
        }

        binding.chatCreateBtn.setOnClickListener {
            val userid = PreferenceUtil.prefs.getString("userid", "")
            // DB에 채팅방 정보 저장 (송신자, 수신자)
//            createChatRoom(
//                ChatRoomInfoModel
//                    (
//                    userid,
//                    "SNick",
//                    "https://image.ytn.co.kr/general/jpg/2022/1118/202211181457199274_d.jpg",
//                    "SAdre",
//                    otherUserid,
//                    "RNick",
//                    "https://image.ytn.co.kr/general/jpg/2022/1118/202211181457199274_d.jpg",
//                    "RAdre",
//                    lostAndFountId.text.toString(),
//                    "https://image.ytn.co.kr/general/jpg/2022/1118/202211181457199274_d.jpg"
//                )
//            )
            // 채팅방 리스트 엑티비티로 이동

            startActivity(intent)

        }
    }

    // 채팅방 생성 (송신자, 수신자)
    private fun createChatRoom(roomKey: ChatRoomInfoModel) {
        FBRef.chatRoomListRef
            .push()
            .setValue(
                ChatRoomInfoModel(
                    roomKey.senderId,
                    roomKey.senderNickname,
                    roomKey.senderProfile,
                    roomKey.senderAddress,
                    roomKey.receiverId,
                    roomKey.senderNickname,
                    roomKey.senderProfile,
                    roomKey.senderAddress,
                    roomKey.lostAndFoundId,
                    roomKey.lostAndFoundImg
                )
            )
    }


}