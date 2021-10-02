# compose-particle-system
A lightweight particle system for Jetpack Compose.


TODO= GIFs here


## Getting started

1. Add the following dependencies in your `build.gradle` file

```groovy
implementation "me.nikhilchaudhari:quarks:1.0.0-alpha01"
```

2. Call `CreateParticles(...)` composable function

```kotlin

 Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
) {
    CreateParticles(
        modifier = Modifier.fillMaxSize(),
        // Set the initial position particles (From where do you want to shoot/generate particles)
        x = 500f, y = 1000f,
        // Set the velocity of particle in x and y direction
        velocity = Velocity(xDirection = 1f, yDirection = 1f),
        // Set the force acting on particle
        force = Force.Gravity(0f),
        // set acceleration on both x and y direction
        acceleration = Acceleration(0f, 0f),
        // set the desired size of particle that you want
        particleSize = ParticleSize.RandomSizes(25..100),
        // set particle colors or color
        particleColor = ParticleColor.RandomColors(listOf(Color.White, Color.Yellow, Color.Red, Color.Blue)),
        // set the max lifetime and aging factor of a particle
        lifeTime = LifeTime(255f, 0.2f),
        // set the emission type - how do you want to generate particle - as a flow/stream, as a explosion/blast
        emissionType = EmissionType.ExplodeEmission(numberOfParticles = 100),
        // duration of animation 
        durationMillis = 10 * 1000
    )
}
```
3. That's it. You're done. Check the configuration section to apply the required configs to your particle system.

## Configuration

This Particle system (or any other particle system) runs on basic physics principles like velocity, force, acceleration etc. Also the emission of particle can be done in two ways 1. A continuous stream/flow of particle or 2. Explosion/Bursting of particles. To apply various configuration to particle system, here are the things you can configure for a particle and as well as for a system - 

### Setting initial position






