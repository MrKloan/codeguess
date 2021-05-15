package io.fries.codeguess.api.event

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(private val inMemoryEventConsumer: InMemoryEventConsumer) {

    @GetMapping("/events")
    fun events() = inMemoryEventConsumer.events()
}