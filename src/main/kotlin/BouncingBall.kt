package com.naulian.processing

import processing.core.PApplet
import processing.core.PApplet.QUARTER_PI
import processing.core.PVector

class Ball(
    position: Position,
    velocity: Velocity,
    val radius: Float,
    val color: Triple<Float, Float, Float>,  // RGB
) : Thing(position, velocity)

fun random(low: Float, high: Float): Float {
    return companion.random(low, high)
}

fun createBall(): Ball {
    return Ball(
        Position(0f, 100f),
        Velocity(PVector.fromAngle(-QUARTER_PI) * random(2f, 10f)),  // random velocity
        random(10f, 30f),                                            // random radius
        Triple(random(0f, 255f), random(0f, 255f), random(0f, 255f))
    )   // random color
}


val balls = mutableListOf(
    createBall(), createBall(), createBall(), createBall(),
    createBall(), createBall(), createBall(), createBall()
)

val Gravity = Acceleration(0f, 0.5f)

fun PApplet.bounce(g: Thing) =
    if (g is Ball) {
        // Simulate bouncing: for any balls below the bottom ...
        if (g.position.y > height - g.radius)
            Pair(                      // keep it in screen, and ...
                Position(g.position.x, (height - g.radius).toFloat()),
                Velocity(g.velocity.x, g.velocity.y * -0.85f)
            )
        // reverse velocity's y-component.
        // coefficient of restitution = 0.85
        else
            Pair(g.position, g.velocity)
    } else {
        throw IllegalArgumentException()
    }
