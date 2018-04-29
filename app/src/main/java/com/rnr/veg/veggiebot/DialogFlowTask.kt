package com.rnr.veg.veggiebot

import android.content.Context
import android.os.AsyncTask
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DialogFlowTask(context: Context) : AsyncTask<String, Void, Message>() {
    var delegate: AsyncResponse? = null
    lateinit var chatService: ChatService
    var dftContext: Any

    init {
        this.dftContext = context
    }

    override fun doInBackground(vararg params: String): Message? {
        chatService = ChatService(this.dftContext as Context)
        return chatService.getVeggieBotResponse(params[0])
    }

    override fun onPostExecute(hajimeMessage: Message?) {
        delegate!!.processFinish(hajimeMessage?:
        Message("J'ai pas compris lol",
                User("VeggieBot",null),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM"))))
    }

}

