package com.rnr.veg.veggiebot

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class Message(var text: String?, val sender: User, val sentDate: String)

val messageMapper = jacksonObjectMapper()