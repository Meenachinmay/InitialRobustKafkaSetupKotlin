package org.polarmeet.meetinglinkservice.consumer

import mu.KotlinLogging
import org.polarmeet.meetinglinkservice.model.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class KafkaMessageConsumer {

    @KafkaListener(topics = ["\${kafka.topic.name}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(message: Message) {
        logger.info { "Received message: $message" }
        // Add your message processing logic here
        processMessage(message)
    }

    private fun processMessage(message: Message) {
        logger.info { "Processing message: ${message.id}" }
        // Add your business logic here
    }
}