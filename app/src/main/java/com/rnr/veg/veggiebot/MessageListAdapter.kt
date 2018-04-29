package com.rnr.veg.veggiebot

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MessageListAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<MessageHolder>() {
    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    // Inflates the appropriate layout according to the ViewType.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = if(viewType == VIEW_TYPE_MESSAGE_SENT) LayoutInflater.from(parent.context)
                .inflate(R.layout.message_sent, parent, false)
        else LayoutInflater.from(parent.context)
                .inflate(R.layout.message_received, parent, false)
        return MessageHolder(view)

    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    // Determines the appropriate ViewType according to the sender of the message.
    override fun getItemViewType(position: Int): Int {
        val messageSender = messageList.get(position).sender.nickname
        return if(messageSender.equals("VeggieBot")){
            VIEW_TYPE_MESSAGE_RECEIVED
        } else VIEW_TYPE_MESSAGE_SENT
    }
}