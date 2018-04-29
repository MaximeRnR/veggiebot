package com.rnr.veg.veggiebot
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var messageText: TextView = itemView.findViewById(R.id.text_message_body)
    var timeText: TextView = itemView.findViewById(R.id.text_message_time)
    var nameText: TextView = itemView.findViewById(R.id.text_message_name)

    fun bind(message: Message) {
        messageText.text = message.text
        timeText.text = message.sentDate
        nameText.text = message.sender.nickname
    }
}