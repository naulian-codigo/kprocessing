package com.naulian.processing

import processing.core.PApplet.RADIUS

fun main() {
    settings {
        size(720, 360)
    }

    setup {
        frameRate(30f)
        ellipseMode(RADIUS)
        background(255)
    }

    draw {
        background(255)

        balls.forEach {
            it.apply(Gravity)
            it.updatePosition(::bounce)
        }

        // Remove balls out of screen
        balls.removeAll {
            it.position.x > width + it.radius || it.position.x < -it.radius
        }

        // Create new balls to keep 8 balls in screen
        for (i in balls.size..7) {
            val ball = createBall()
            balls.add(ball)
        }

        balls.forEach { ball ->
            stroke(ball.color, 255)
            fill(ball.color, 180)
            circle(ball.position, ball.radius)
        }
    }
}
