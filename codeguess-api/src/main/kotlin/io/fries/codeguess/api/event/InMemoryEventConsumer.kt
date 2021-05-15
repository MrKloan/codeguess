package io.fries.codeguess.api.event

import io.fries.codeguess.amqp.AmqpConfiguration
import io.fries.codeguess.amqp.AmqpReceiver
import io.fries.codeguess.core.event.EventConsumer
import io.fries.codeguess.core.event.GameEvent
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Component
@Import(AmqpConfiguration::class, AmqpReceiver::class)
class InMemoryEventConsumer : EventConsumer {

    private val events = mutableListOf<GameEvent>()

    override fun consume(event: GameEvent) {
        events.add(event)
    }

    fun events() = events.toList()
}