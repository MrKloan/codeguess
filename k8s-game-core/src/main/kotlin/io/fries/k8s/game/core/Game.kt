package io.fries.k8s.game.core

import io.fries.k8s.game.pdk.Guesser

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