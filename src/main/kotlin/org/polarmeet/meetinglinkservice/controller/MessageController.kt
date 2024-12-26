package org.polarmeet.meetinglinkservice.controller

import mu.KotlinLogging
import org.polarmeet.meetinglinkservice.model.Message
import org.polarmeet.meetinglinkservice.service.MessageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/messages")
class MessageController(
    private val messageService: MessageService
) {

    @PostMapping
    fun sendMessage(@RequestBody message: Message): Mono<Message> {
        logger.info { "Received request to send message: $message" }
        return messageService.sendMessage(message)
    }
}
