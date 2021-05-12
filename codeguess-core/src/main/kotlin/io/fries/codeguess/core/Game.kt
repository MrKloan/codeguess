package io.fries.codeguess.core

import io.fries.codeguess.pdk.Guesser

class Game(
        private val messager: Messager,
        private val guesser: Guesser
) {
    fun run(target: Int) {
        while (true) {
            val attempt = guesser.guess()
            messager.send("Attempting to guess target number: $attempt?")

            if (attempt == target) {
                messager.send("Target number found! It was $target.")
                break
            }
        }
    }
}