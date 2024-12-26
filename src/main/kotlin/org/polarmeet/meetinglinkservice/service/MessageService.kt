package org.polarmeet.meetinglinkservice.service

import mu.KotlinLogging
import org.polarmeet.meetinglinkservice.model.Message
import org.polarmeet.meetinglinkservice.producer.KafkaMessageProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

@Service
class MessageService(
    private val kafkaMessageProducer: KafkaMessageProducer,

    @Value("\${kafka.topic.name}")
    private val topicName: String
) {

    fun sendMessage(message: Message): Mono<Message> {
        return Mono.fromFuture(kafkaMessageProducer.sendMessage(topicName, message))
            .map { message }
            .doOnSuccess { logger.info { "Successfully processed message: ${message.id}" } }
            .doOnError { error ->
                logger.error(error) { "Error processing message: ${message.id}" }
            }
    }
}
