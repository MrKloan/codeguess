package io.fries.codeguess.core.event

fun interface EventPublisher {
    fun publish(event: GameEvent)
}