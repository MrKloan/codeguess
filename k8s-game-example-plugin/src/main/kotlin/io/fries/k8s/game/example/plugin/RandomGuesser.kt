package io.fries.k8s.game.example.plugin

import io.fries.k8s.game.pdk.Guesser
import kotlin.random.Random

class RandomGuesser : Guesser {

    override fun guess(): Int {
        return Random.nextInt(0, 10)
    }
}