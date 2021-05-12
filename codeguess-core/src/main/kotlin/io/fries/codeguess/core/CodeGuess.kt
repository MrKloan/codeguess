package io.fries.codeguess.core

import io.fries.codeguess.pdk.Guesser

class CodeGuess(
        private val messager: Messager,
        private val guesser: Guesser
) {
    fun run(target: Int) {
        while (true) {
            val guess = guesser.guess()
            messager.send("Attempting to guess target number: $guess?")

            if (guess == target) {
                messager.send("Target number found! It was $target.")
                break
            }
        }
    }
}