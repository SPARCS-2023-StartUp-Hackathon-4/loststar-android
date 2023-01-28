package com.sparcs.loststar.ui.lostOrFindDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.skydoves.sandwich.onSuccess
import com.sparcs.loststar.databinding.ActivityLostOrFindDetailBinding
import com.sparcs.loststar.network.RetrofitClient
import com.sparcs.loststar.network.model.UserDto
import com.sparcs.loststar.ui.chatting.chatroom.ChatRoomInfoModel
import com.sparcs.loststar.ui.main.MainActivity
import com.sparcs.loststar.util.FBRef
import com.sparcs.loststar.util.GlideUtil
import com.sparcs.loststar.util.PreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostOrFindDetailActivity : AppCompatActivity() {

    private var senderInfo: UserDto = UserDto()
    private var receiverInfo: UserDto = UserDto()
    private var lostFoundImage: String = ""
    private var lostFoundTitle: String = ""

    private val binding: ActivityLostOrFindDetailBinding by lazy {
        ActivityLostOrFindDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra("id", -1)

        // 글 상세 정보 조회
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().getLostOrFound(id).onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    receiverInfo = UserDto(
                        id = data.writer.id,
                        nickname = data.writer.nickname,
                        address = data.writer.address
                    )
                    lostFoundImage = data.image
                    lostFoundTitle = data.title
                    Log.d("분실물 상세 : receiverInfo", receiverInfo.toString())
                    Log.d("분실물 상세 : 이미지", lostFoundImage)

                    // detail 데이터 바인딩
                    binding.tvProductName.text = data.title
                    binding.address.text = data.location
                    binding.locationDetail.text = data.locationDetail
                    binding.category.text = data.category
                    binding.date.text = data.date
                    binding.time.text = data.time
                    binding.description.text = data.description
                    binding.nickname.text = data.writer.nickname
                    binding.star.text = "${data.reward} 별조각"
                    GlideUtil.loadRadiusImage(binding.image, data.image, 8)
                    GlideUtil.loadCircleImage(binding.profile, data.writer.profile)
                }
            }
        }

        // sender 정보 조회
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getApiService().fetchMyInfo().onSuccess {
                CoroutineScope(Dispatchers.Main).launch {
                    senderInfo = UserDto(
                        id = data.id,
                        nickname = data.nickname,
                        address = data.address
                    )
                    Log.d("분실물 상세 : sender 정보", senderInfo.toString())
                }
            }
        }

        binding.chatCreateBtn.setOnClickListener {
            val userid = PreferenceUtil.prefs.getString("userid", "")
            // DB에 채팅방 정보 저장 (송신자, 수신자)
            createChatRoom(ChatRoomInfoModel(
                senderId = senderInfo.id.toString(),
                senderNickname = senderInfo.nickname,
                senderProfile = "https://image.ytn.co.kr/general/jpg/2022/1118/202211181457199274_d.jpg",
                senderAddress = senderInfo.address,
                receiverId = receiverInfo.id.toString(),
                receiverNickname = receiverInfo.nickname,
                receiverProfile = "https://image.ytn.co.kr/general/jpg/2022/1118/202211181457199274_d.jpg",
                receiverAddress = receiverInfo.address,
                lostAndFoundTitle = lostFoundTitle,
                lostAndFoundImg = lostFoundImage
            ))
            val intent = Intent(this@LostOrFindDetailActivity, MainActivity::class.java)
            intent.putExtra("goToChatting", true)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
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
                    roomKey.lostAndFoundTitle,
                    roomKey.lostAndFoundImg
                )
            )
    }
}