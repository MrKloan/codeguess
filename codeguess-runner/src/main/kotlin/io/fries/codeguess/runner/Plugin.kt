package io.fries.codeguess.runner

import io.fries.codeguess.pdk.Guesser
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path

@Component
data class Plugin(
        @Value("\${PLUGIN_PATH}") private val filePath: String,
        @Value("\${PLUGIN_MAIN}") private val mainClass: String
) {
    fun load(): Guesser {
        val classPathUrl: URL = Path.of(filePath).toUri().toURL()
        val classLoader = URLClassLoader(arrayOf(classPathUrl), this.javaClass.classLoader)
        val type = classLoader.loadClass(mainClass)
        val constructor = type.getDeclaredConstructor()
        constructor.isAccessible = true

        return constructor.newInstance() as Guesser
    }
}