package io.fries.codeguess.runner

import io.fries.codeguess.amqp.AmqpConfiguration
import io.fries.codeguess.amqp.AmqpSender
import io.fries.codeguess.core.CodeGuess
import io.fries.codeguess.core.event.EventPublisher
import io.fries.codeguess.core.event.GameEvent
import io.fries.codeguess.pdk.Guesser
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import kotlin.random.Random
import kotlin.system.exitProcess

@SpringBootApplication
@Import(AmqpConfiguration::class, AmqpSender::class)
class Application(private val eventPublisher: EventPublisher) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        try {
            val guesser = loadGuesser(args)
            val target = Random.nextInt(0, 10)
            CodeGuess(eventPublisher, guesser).run(target)
        } catch (exception: Exception) {
            help()
            exitProcess(1)
        }
    }

    private fun loadGuesser(args: ApplicationArguments): Guesser {
        val filePath = args.getOptionValues("plugin-path")[0]
        val mainClass = args.getOptionValues("plugin-main")[0]
        val plugin = Plugin(filePath, mainClass)

        return plugin.load()
    }

    private fun help() {
        eventPublisher.publish(GameEvent("""
            Usage: codeguess [OPTIONS]
            --plugin-path: The path to the plugin.
            --plugin-main: The main class of the plugin, which will be loaded by CodeGuess.
        """.trimIndent()))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args).close()
}