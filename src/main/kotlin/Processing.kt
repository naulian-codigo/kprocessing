package com.naulian.processing

import processing.core.PApplet

class ProcessingCompanion : PApplet()

val companion = ProcessingCompanion()

fun settings(block: PApplet.() -> Unit) {
    Processing.settings(block)
}

fun setup(block: PApplet.() -> Unit) {
    Processing.setup(block)
}

fun draw(block: PApplet.() -> Unit) {
    Processing.draw(block)
}

object Processing {
    var _settings: (PApplet.() -> Unit)? = null
    var _setup: (PApplet.() -> Unit)? = null
    var _draw: (PApplet.() -> Unit)? = null

    fun settings(block: PApplet.() -> Unit) {
        _settings = block
    }

    fun setup(block: PApplet.() -> Unit) {
        _setup = block
    }

    fun draw(block: PApplet.() -> Unit) {
        _draw = block

        run()
    }

    private fun run() {
        PApplet.main(ProcessingApp::class.java)
    }
}

class ProcessingApp : PApplet() {

    override fun settings() {
        Processing._settings?.invoke(this)
    }

    override fun setup() {
        Processing._setup?.invoke(this)
    }

    override fun draw() {
        Processing._draw?.invoke(this)
    }
}