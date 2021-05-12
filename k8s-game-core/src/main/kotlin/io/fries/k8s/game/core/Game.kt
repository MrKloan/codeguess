package io.fries.k8s.game.core

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Game : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("K8s Game")
    }
}

fun main(args: Array<String>) {
    Game().run(*args)
}