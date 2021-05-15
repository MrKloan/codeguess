package io.fries.codeguess.amqp

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AmqpConfiguration {

    @Bean
    open fun fanoutExchange() = FanoutExchange("codeguess")

    @Bean
    open fun queue() = Queue("codeguess")

    @Bean
    open fun binding(queue: Queue, fanoutExchange: FanoutExchange): Binding = BindingBuilder.bind(queue).to(fanoutExchange)
}