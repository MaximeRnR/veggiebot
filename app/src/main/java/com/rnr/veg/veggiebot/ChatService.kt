package com.rnr.veg.veggiebot


import ai.api.AIServiceException
import ai.api.android.AIConfiguration
import ai.api.android.AIDataService
import ai.api.model.AIRequest
import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChatService(context:Context?) {


    private val configuration = AIConfiguration("",
            ai.api.AIConfiguration.SupportedLanguages.fromLanguageTag("FR"),
            AIConfiguration.RecognitionEngine.System)
    private val dataService = AIDataService(context!!,configuration)

    private val veggieBot = User("VeggieBot", null)

    fun getVeggieBotResponse(text: String?): Message {
        val request = AIRequest(text)
        val botResponse: String
        try {
            val response = dataService.request(request)
            if (200 == response.status.code) {
                botResponse = response.result.fulfillment.speech
                return Message(botResponse,veggieBot, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM")))
            }
        } catch (e: AIServiceException) {
            e.printStackTrace()
        }
        return Message("J'ai pas compris",veggieBot, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM")))

    }


}