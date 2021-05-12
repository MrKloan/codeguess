package io.fries.k8s.game.core

import io.fries.k8s.game.pdk.Guesser
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.system.exitProcess

@SpringBootApplication
class Application : CommandLineRunner {

    override fun run(vararg args: String?) {
        val messager = Messager { message -> println(message) }
        val guesser = loadGuesser(args, messager)

        Game(messager, guesser).run(7)
    }

    private fun loadGuesser(args: Array<out String?>, messager: Messager): Guesser {
        if (args.size != 2) {
            messager.send("Invalid arguments: ${listOf(*args)}")
            exitProcess(1)
        }

        val plugin = Plugin(args[0]!!, args[1]!!)
        return plugin.load()
    }
}

fun main(args: Array<String>) {
    Application().run(*args)
}