package io.fries.codeguess.runner

import io.fries.codeguess.amqp.AmqpConfiguration
import io.fries.codeguess.amqp.AmqpSender
import io.fries.codeguess.core.CodeGuess
import io.fries.codeguess.core.event.EventPublisher
import io.fries.codeguess.core.event.GameEvent
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import kotlin.random.Random
import kotlin.system.exitProcess

@SpringBootApplication
@Import(AmqpConfiguration::class, AmqpSender::class)
class Application(
        private val plugin: Plugin,
        private val eventPublisher: EventPublisher
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        try {
            val guesser = plugin.load()
            val target = Random.nextInt(0, 10)
            CodeGuess(eventPublisher, guesser).run(target)
        } catch (exception: Exception) {
            help(exception.message)
            exitProcess(1)
        }
    }

    private fun help(message: String?) {
        eventPublisher.publish(GameEvent("""
            An error occurred: $message
            
            Usage: codeguess [OPTIONS]
            --plugin-path: The path to the plugin.
            --plugin-main: The main class of the plugin, which will be loaded by CodeGuess.
        """.trimIndent()))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args).close()
}