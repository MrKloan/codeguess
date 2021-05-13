package io.fries.codeguess.amqp

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AmqpConfiguration {

    @Bean
    open fun fanoutExchange() = FanoutExchange("codeguess")

    @Bean
    open fun queue() = AnonymousQueue()

    @Bean
    open fun binding(queue: Queue, fanoutExchange: FanoutExchange): Binding = BindingBuilder.bind(queue).to(fanoutExchange)

    @Bean
    open fun receiver() = Receiver()

    @Bean
    open fun sender(rabbitTemplate: RabbitTemplate, fanoutExchange: FanoutExchange) = Sender(rabbitTemplate, fanoutExchange)
}