package io.fries.codeguess.core.event

fun interface EventConsumer {
    fun consume(event: GameEvent)
}