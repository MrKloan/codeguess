package io.fries.codeguess.amqp

import io.fries.codeguess.core.event.GameEvent
import io.fries.codeguess.core.event.EventPublisher
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class AmqpSender(
        private val rabbitTemplate: RabbitTemplate,
        private val fanoutExchange: FanoutExchange
) : EventPublisher {

    private val logger = LoggerFactory.getLogger(AmqpSender::class.java)

    override fun publish(event: GameEvent) {
        logger.info("Sending: $event")
        rabbitTemplate.convertAndSend(fanoutExchange.name, "", event)
    }
}
