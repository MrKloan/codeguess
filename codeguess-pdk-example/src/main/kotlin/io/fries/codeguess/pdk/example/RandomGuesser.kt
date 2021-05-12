package io.fries.codeguess.pdk.example

import io.fries.codeguess.pdk.Guesser
import kotlin.random.Random

class RandomGuesser : Guesser {

    override fun guess(): Int {
        return Random.nextInt(0, 10)
    }
}