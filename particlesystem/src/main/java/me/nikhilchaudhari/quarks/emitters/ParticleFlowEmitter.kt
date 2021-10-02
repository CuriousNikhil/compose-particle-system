package me.nikhilchaudhari.quarks.emitters

import me.nikhilchaudhari.quarks.particle.EmissionType
import me.nikhilchaudhari.quarks.particle.ParticleConfigData

internal class ParticleFlowEmitter(
    private val durationMillis: Int,
    private val emissionConfig: EmissionType.FlowEmission,
    particleConfigData: ParticleConfigData
) : Emitter(particleConfigData) {

    private var particleCount = 0
    private var elapsed = 0f
    private var elapsedTimeParticleCreation = 0f

    override fun generateParticles(numberOfParticles: Int) {
        if (this.isFull()) {
            return
        }
        particleCount++
        repeat(numberOfParticles) { addParticle() }
    }

    private fun isTimeElapsed(): Boolean {
        return when (durationMillis) {
            0 -> false
            EmissionType.FlowEmission.INDEFINITE -> false
            else -> elapsed >= durationMillis
        }
    }

    private fun isFull(): Boolean = emissionConfig.maxParticlesCount in 1..(particleCount)

    override fun update(dt: Float) {
        elapsedTimeParticleCreation += dt
        if (elapsedTimeParticleCreation >= 1 && !isTimeElapsed()) {
            val amount = (emissionConfig.emissionRate * elapsedTimeParticleCreation).toInt()
            generateParticles(amount)
            elapsedTimeParticleCreation %= 1
        }
        elapsed += dt

        for (i in particlePool.size - 1 downTo 0) {
            val particle = particlePool[i]
            particle.update(dt)
        }
        particlePool.removeAll { it.finished() }
    }
}
