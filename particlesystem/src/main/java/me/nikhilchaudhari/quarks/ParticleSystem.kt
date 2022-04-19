package me.nikhilchaudhari.quarks

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import me.nikhilchaudhari.quarks.emitters.ParticleExplodeEmitter
import me.nikhilchaudhari.quarks.emitters.ParticleFlowEmitter
import me.nikhilchaudhari.quarks.particle.*

@Composable
fun CreateParticles(
    modifier: Modifier = Modifier,
    x: Float = 0f,
    y: Float = 0f,
    velocity: Velocity = Velocity(xDirection = 1f, yDirection = 1f),
    force: Force = Force.Gravity(0.0f),
    acceleration: Acceleration = Acceleration(0f, 0f),
    particleImage: ParticleImage = ParticleImage.Images(emptyList()),
    particleSize: ParticleSize = ParticleSize.ConstantSize(),
    particleColor: ParticleColor = ParticleColor.SingleColor(),
    lifeTime: LifeTime = LifeTime(255f, 1f),
    emissionType: EmissionType = EmissionType.ExplodeEmission(),
    durationMillis: Int = 10000,
    onParticlesStopped: (() -> Unit)? = null
) {
    var startTime by remember { mutableStateOf(0L) }
    var currentTime by remember { mutableStateOf(System.nanoTime()) }
    var previousTime by remember { mutableStateOf(System.nanoTime()) }

    val emitter = remember {
        val particleConfigData = ParticleConfigData(
            x,
            y,
            velocity,
            force,
            acceleration,
            particleImage,
            particleSize,
            particleColor,
            lifeTime,
            emissionType
        )
        when (emissionType) {
            is EmissionType.ExplodeEmission -> {
                ParticleExplodeEmitter(emissionType.numberOfParticles, particleConfigData)
            }
            is EmissionType.FlowEmission -> {
                ParticleFlowEmitter(
                    durationMillis,
                    emissionType,
                    particleConfigData
                )
            }
        }
    }

    startTime = System.currentTimeMillis()
    LaunchedEffect(Unit) {
        val condition = if (emissionType is EmissionType.FlowEmission &&
            emissionType.maxParticlesCount == EmissionType.FlowEmission.INDEFINITE
        ) {
            true
        } else {
            System.currentTimeMillis() - startTime < durationMillis
        }

        var particlesStoppedInvoked = false
        while (condition) {
            withFrameNanos {
                previousTime = currentTime
                currentTime = it

                val isTimeElapsed = (System.currentTimeMillis() - startTime) > durationMillis
                if (!particlesStoppedInvoked && isTimeElapsed) {
                    onParticlesStopped?.invoke()
                    particlesStoppedInvoked = true
                }
            }
        }
    }

    Canvas(modifier) {
        emitter.render(this)
        emitter.applyForce(force.createForceVector())
        emitter.update(((currentTime - previousTime) / 1E7).toFloat())
    }
}
