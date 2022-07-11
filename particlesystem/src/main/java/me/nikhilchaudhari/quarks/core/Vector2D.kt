package me.nikhilchaudhari.quarks.core

open class Vector2D(var x: Float = 0f, var y: Float = 0f)

fun Vector2D.add(other: Vector2D): Vector2D {
    this.x += other.x
    this.y += other.y
    return this
}

fun Vector2D.add(other: Vector2D, scalar: Float): Vector2D {
    this.x += other.x * scalar
    this.y += other.y * scalar
    return this
}

fun Vector2D.scalarMultiply(scalar: Float): Vector2D {
    this.x *= scalar
    this.y *= scalar
    return this
}

fun Vector2D.inc(factor: Float): Vector2D {
    this.x += factor
    this.y += factor
    return this
}
