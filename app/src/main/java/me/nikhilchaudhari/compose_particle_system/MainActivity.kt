package me.nikhilchaudhari.compose_particle_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.nikhilchaudhari.compose_particle_system.ui.theme.ComposeparticlesystemTheme
import me.nikhilchaudhari.quarks.CreateParticles
import me.nikhilchaudhari.quarks.particle.Acceleration
import me.nikhilchaudhari.quarks.particle.EmissionType
import me.nikhilchaudhari.quarks.particle.Force
import me.nikhilchaudhari.quarks.particle.LifeTime
import me.nikhilchaudhari.quarks.particle.ParticleColor
import me.nikhilchaudhari.quarks.particle.ParticleSize
import me.nikhilchaudhari.quarks.particle.Velocity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeparticlesystemTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    CreateParticles(
                        modifier = Modifier
                            .fillMaxSize(), x = 500f, y = 1000f,
                        velocity = Velocity(xDirection = 1f, yDirection = 1f),
                        force = Force.Gravity(0f),
                        acceleration = Acceleration(0f, 0f),
                        particleSize = ParticleSize.RandomSizes(25..100),
                        particleColor = ParticleColor.RandomColors(listOf(Color.White, Color.Yellow, Color.Red, Color.Blue)),
                        lifeTime = LifeTime(255f, 0.2f),
                        emissionType = EmissionType.ExplodeEmission(numberOfParticles = 100),
                        durationMillis = 10 * 1000
                    )
                }
            }
        }
    }
}
