package org.polarmeet.meetinglinkservice.producer

import mu.KotlinLogging
import org.polarmeet.meetinglinkservice.model.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

private val logger = KotlinLogging.logger {}

@Component
class KafkaMessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    fun sendMessage(topic: String, message: Message): CompletableFuture<SendResult<String, Any>> {
        logger.info { "Attempting to send message: $message to topic: $topic" }

        val future = kafkaTemplate.send(topic, message.id, message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                logger.info {
                    "Successfully sent message: ${message.id} to topic: $topic " +
                            "partition: ${result.recordMetadata.partition()}"
                }
            } else {
                logger.error(ex) { "Failed to send message: ${message.id} to topic: $topic" }
            }
        }

        return future
    }
}