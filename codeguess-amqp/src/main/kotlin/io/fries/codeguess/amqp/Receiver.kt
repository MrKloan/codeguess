package io.fries.codeguess.amqp

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener

class Receiver {

    private val logger = LoggerFactory.getLogger(Receiver::class.java)

    @RabbitListener(queues = ["#{queue.name}"])
    fun receive(message: String) {
        logger.info("Received: <$message>")
    }
}