package me.nikhilchaudhari.quarks.particle

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import me.nikhilchaudhari.quarks.core.TWO_PI
import me.nikhilchaudhari.quarks.core.Vector2D
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Velocity(val xDirection: Float = 0f, val yDirection: Float = 0f, val angle: Double = TWO_PI, val randomize: Boolean = true)

internal fun Velocity.createVelocityVector(): Vector2D {
    return if (this.randomize) {
        Vector2D(
            x = (this.xDirection * cos(angle * Random.nextFloat())).toFloat(),
            y = (this.yDirection * sin(angle * Random.nextFloat())).toFloat()
        )
    } else {
        Vector2D(
            x = (this.xDirection * cos(angle)).toFloat(),
            y = (this.yDirection * sin(angle)).toFloat()
        )
    }
}

data class Acceleration(val xComponent: Float = 0f, val yComponent: Float = 0f, val uniform: Boolean = false)

internal fun Acceleration.createAccelerationVector(): Vector2D {
    return if (!uniform) {
        Vector2D(xComponent * Random.nextFloat(), yComponent * Random.nextFloat())
    } else {
        Vector2D(this.xComponent, this.yComponent)
    }
}

sealed class Force {
    data class Gravity(val magnitude: Float = 0f) : Force()
    data class Wind(val xDirection: Float = 0f, val yDirection: Float = 0f) : Force()
}

internal fun Force.createForceVector(): Vector2D {
    return when (this) {
        is Force.Gravity -> {
            Vector2D(0f, this.magnitude)
        }
        is Force.Wind -> {
            Vector2D(this.xDirection, this.yDirection)
        }
    }
}

sealed class ParticleSize {
    data class ConstantSize(val size: Float = 25f) : ParticleSize()
    data class RandomSizes(val range: IntRange = 25..50) : ParticleSize()
}

internal fun ParticleSize.getExactSize(): Float {
    return when (this) {
        is ParticleSize.ConstantSize -> this.size
        is ParticleSize.RandomSizes -> (Random.nextInt(this.range.first, this.range.last)).toFloat()
    }
}

sealed class ParticleColor {
    data class SingleColor(val color: Color = Color.Yellow) : ParticleColor()
    data class RandomColors(val colors: List<Color>) : ParticleColor()
}

internal fun ParticleColor.getExactColor(): Color {
    return when (this) {
        is ParticleColor.SingleColor -> this.color
        is ParticleColor.RandomColors -> this.colors[Random.nextInt(0, this.colors.size)]
    }
}

data class LifeTime(val maxLife: Float = 255f, val agingFactor: Float = 15f)

sealed class EmissionType {
    data class ExplodeEmission(
        val numberOfParticles: Int = 30
    ) : EmissionType()

    data class FlowEmission(
        val maxParticlesCount: Int = 50,
        val emissionRate: Float = 0.5f
    ) : EmissionType() {
        companion object {
            const val INDEFINITE = -2
        }
    }
}

internal data class ParticleConfigData(
    val x: Float = 0f,
    val y: Float = 0f,
    val velocity: Velocity,
    val force: Force,
    val acceleration: Acceleration,
    val particleSize: ParticleSize,
    val particleColor: ParticleColor,
    val particleImageBitmap: ImageBitmap?,
    val lifeTime: LifeTime,
    val emissionType: EmissionType
)
