package io.fries.codeguess.amqp

import io.fries.codeguess.core.event.EventConsumer
import io.fries.codeguess.core.event.GameEvent
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class AmqpReceiver(private val eventConsumer: EventConsumer) {

    private val logger = LoggerFactory.getLogger(AmqpReceiver::class.java)

    @RabbitListener(queues = ["#{queue.name}"])
    fun receive(event: GameEvent) {
        logger.info("Received: $event")
        eventConsumer.consume(event)
    }
}