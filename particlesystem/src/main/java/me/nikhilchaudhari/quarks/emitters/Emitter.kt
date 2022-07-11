package me.nikhilchaudhari.quarks.emitters

import androidx.compose.ui.graphics.drawscope.DrawScope
import me.nikhilchaudhari.quarks.core.Vector2D
import me.nikhilchaudhari.quarks.particle.Particle
import me.nikhilchaudhari.quarks.particle.ParticleConfigData
import me.nikhilchaudhari.quarks.particle.createAccelerationVector
import me.nikhilchaudhari.quarks.particle.createVelocityVector
import me.nikhilchaudhari.quarks.particle.getExactColor
import me.nikhilchaudhari.quarks.particle.getExactSize

internal abstract class Emitter(
    private val particleConfigData: ParticleConfigData
) {

    val particlePool = mutableListOf<Particle>()

    abstract fun generateParticles(numberOfParticles: Int)

    fun addParticle() {
        val particle = createFreshParticle()
        particlePool.add(particle)
    }

    private fun createFreshParticle(): Particle {
        return Particle(
            initialX = particleConfigData.x,
            initialY = particleConfigData.y,
            color = particleConfigData.particleColor.getExactColor(),
            size = particleConfigData.particleSize.getExactSize(),
            velocity = particleConfigData.velocity.createVelocityVector(),
            acceleration = particleConfigData.acceleration.createAccelerationVector(),
            lifetime = particleConfigData.lifeTime.maxLife,
            agingFactor = particleConfigData.lifeTime.agingFactor,
            image = particleConfigData.particleImageBitmap
        )
    }

    fun applyForce(force: Vector2D) {
        for (particle in particlePool) {
            particle.applyForce(force)
        }
    }

    abstract fun update(dt: Float)

    fun render(drawScope: DrawScope) {
        for (particle in particlePool) {
            particle.show(drawScope)
        }
    }
}
