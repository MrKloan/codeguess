package io.fries.codeguess.amqp

import io.fries.codeguess.core.Messager
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate

class Sender(
        private val rabbitTemplate: RabbitTemplate,
        private val fanoutExchange: FanoutExchange
) : Messager {

    private val logger = LoggerFactory.getLogger(Sender::class.java)

    override fun send(message: String) {
        logger.info("Sending: <$message>")
        rabbitTemplate.convertAndSend(fanoutExchange.name, "", message)
    }
}
