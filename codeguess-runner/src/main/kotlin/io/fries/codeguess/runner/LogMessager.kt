package io.fries.codeguess.runner

import io.fries.codeguess.core.Messager
import org.slf4j.LoggerFactory

class LogMessager : Messager {

    private val logger = LoggerFactory.getLogger(LogMessager::class.java)

    override fun send(message: String) {
        logger.info(message)
    }
}