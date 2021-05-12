package io.fries.k8s.game.core

fun interface Messager {
    fun send(message: String)
}