package com.rnr.veg.veggiebot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChatActivity : AppCompatActivity(), AsyncResponse {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var veggieTyping: TextView
    private var messageList = mutableListOf(
            Message("Hello ! Je suis VeggieBot et je suis là pour répondre à tes questions sur le véganisme :) Alors n'hésite pas !",
                    User("VeggieBot",null),
                    formattedTimeNow())
    )

    override fun processFinish(output: Any?) {
        when(output) {
            is Message ->  sendAndNotify(output)
            else -> println("bug")
        }
        hideVeggieTyping()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_chat)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MessageListAdapter(messageList)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        veggieTyping = findViewById(R.id.typing_text)
        veggieTyping.visibility=View.INVISIBLE

        val sendButton = findViewById(R.id.button_chatbox_send) as Button
        val editText = findViewById(R.id.edittext_chatbox) as EditText
        sendButton.setOnClickListener {
            val text: String? = editText.text.toString()
            if(!text.equals("")) {
                val messageFromUser = Message(text,
                        User("Moi", null),
                        formattedTimeNow())
                sendAndNotify(messageFromUser)
                editText.text = null
                veggieResponse(text)
            }
        }
    }

    private fun veggieResponse(text: String?) {
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        veggieTyping.visibility=View.VISIBLE
        veggieTyping.startAnimation(slideUp)
        val dialogFlowTask = DialogFlowTask(this.applicationContext)
        dialogFlowTask.delegate = this
        dialogFlowTask.execute(text)
    }

    private fun sendAndNotify(message: Message) {
        sendMessage(message)
        viewAdapter.notifyDataSetChanged()
        recyclerView.smoothScrollToPosition(messageList.size - 1)
    }

    private fun sendMessage(message: Message) = messageList.add(message)

    private fun hideVeggieTyping() {
        val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        veggieTyping.visibility = View.INVISIBLE
        veggieTyping.startAnimation(slideDown)
    }

    private fun formattedTimeNow(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
