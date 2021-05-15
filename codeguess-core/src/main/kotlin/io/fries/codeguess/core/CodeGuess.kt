package io.fries.codeguess.core

import io.fries.codeguess.core.event.EventPublisher
import io.fries.codeguess.core.event.GameEvent
import io.fries.codeguess.pdk.Guesser

class CodeGuess(
        private val eventPublisher: EventPublisher,
        private val guesser: Guesser
) {
    fun run(target: Int) {
        while (true) {
            val guess = guesser.guess()
            eventPublisher.publish(GameEvent("Attempting to guess target number: $guess?"))

            if (guess == target) {
                eventPublisher.publish(GameEvent("Target number found! It was $target."))
                break
            }
        }
    }
}