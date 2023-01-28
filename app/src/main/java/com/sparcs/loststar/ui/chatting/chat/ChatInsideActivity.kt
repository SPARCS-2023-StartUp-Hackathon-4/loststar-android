package com.sparcs.loststar.ui.chatting.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.sparcs.loststar.R
import com.sparcs.loststar.util.FBRef
import com.sparcs.loststar.util.PreferenceUtil
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ChatInsideActivity : AppCompatActivity() {
    private var roomKey = ""
    lateinit var databaseRef: DatabaseReference
    lateinit var valueEvent: ValueEventListener
    val chatData = mutableListOf<ChatModel>()

    val time = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("MM월dd일 hh:mm")
    val curTime = dateFormat.format(Date(time)).toString()

    lateinit var chat_rvAdapter: ChatListRVAdapter

    val userid = PreferenceUtil.prefs.getString("userid", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_inside)

        roomKey = intent.getStringExtra("roomKey").toString()

        val chat_rv = findViewById<RecyclerView>(R.id.rv_RecyclerViewMessage)
        chat_rvAdapter = ChatListRVAdapter(chatData)
        chat_rv.adapter = chat_rvAdapter
        chat_rv.layoutManager = LinearLayoutManager(this)

        if (roomKey != null) {
            readChat(roomKey)
        }

        val inputBtn = findViewById<Button>(R.id.btn_messageInputBtn)
        val inputText = findViewById<EditText>(R.id.edt_messageEdit)
        inputBtn.setOnClickListener {
            Handler().postDelayed({
                if (inputText.text.toString() == null) {
                    Toast.makeText(this, "채팅을 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    insertChat(roomKey, inputText.text.toString())
                }
            }, 100)
        }
        val backBtn = findViewById<ImageView>(R.id.iv_messageBack)
        backBtn.setOnClickListener {
            databaseRef.removeEventListener(valueEvent)
            finish()
        }

        val resolveBtn = findViewById<Button>(R.id.btn_resolve)
        resolveBtn.setOnClickListener {
            // 서버에 요청 보내야 됨
            // 모달 창 띄워주기
            // finish()
        }
    }

    private fun insertChat(roomKey: String?, inputText: String) {
        val inputText2 = findViewById<EditText>(R.id.edt_messageEdit)
        if (roomKey != null) {
            FBRef.chatRef
                .child(roomKey)
                .push()
                .setValue(
                    ChatModel(
                        userid,
                        inputText,
                        curTime
                    )
                )
        }
        inputText2.setText("")
        chat_rvAdapter.notifyDataSetChanged()
    }

    // 채팅 읽기
    private fun readChat(roomKey: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatData.clear()

                for (dataModel in snapshot.children) {
                    try {
                        val item = dataModel.getValue(ChatModel::class.java)
                        val item2 = dataModel.key
                        if (item != null) {
                        }
                        if (item != null) {
                            chatData.add(item)
                        }
                    } catch (e: Exception) {
                        Log.d("chatchat", e.toString())
                    }
                }
                chat_rvAdapter.notifyDataSetChanged()

                val chatRv = findViewById<RecyclerView>(R.id.rv_RecyclerViewMessage)
                chatRv?.scrollToPosition(chatData.size - 1)
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        val postListener2 = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        FBRef.chatRoomListRef.child(roomKey).addValueEventListener(postListener2)
        databaseRef = FBRef.chatRef.child(roomKey)
        valueEvent = databaseRef.addValueEventListener(postListener)
    }
}