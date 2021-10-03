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

### Position

Set the initial position of particle emitter, from where do you want to start the emission. Set position `x = 500f` and `y = 500f`. This is canvas, coordinate system starts from top left corner. Horizontal - right direction is positive x axis and vertical-downwards is positive y axis.

```kotlin
 CreateParticles(
   //...
    x = 500f, y = 1000f,
  //...
)
```

### Emission Type

Set emission type to set particle emission as flow/stream of steady particles or as explosion at once.

#### EmissionType.ExplodeEmission 🎊

If you want to explode/burst particles at once, you can set emission type to `emissionType = EmissionType.ExplodeEmission(numberOfParticles = 100)`. Pass the number of particles that you want at the time of explosion. 

// TODO - add gif

#### EmissionType.FlowEmission 💧

**Definite Emission**

If you want a slow and steady stream/flow of _constant number of particles_, set 
`emissionType = EmissionType.FlowEmission(maxParticlesCount = 500, emissionRate = 0.6f)`. 

Pass the maximum number of particles you want for ex, `maxParticlesCount = 500`. This will create a steady flow of 500 particles. 


**Indefinite Emission** 

If you want an indefinite stream/flow of particles you can set `maxParticlesCount = EmissionType.FlowEmission.INDEFINITE`. This will create an indefinite steady flow of _infinite number of particles_.

⚠️  _`durationMillis = 10000` config does not work for indefinite emission. It'll run continuously, so make sure you only use it whenever needed. The stopping mechanism is yet to be added in the code._



### Duration ⏰

Set the duration value `durationMillis = 10000` for how long do you want to run the animation. Your animation of particle will run for the given number of milliseconds. Duration won't work for indefinte emission of particles.

_Default value_ = 10000


### Velocity 🚤

You can define the particle velocity in both directions. Set `velocity = Velocity(xDirection = 1f, yDirection = 1f)`. 

_Default value_ = `Velocity(xDirection = 1f, yDirection = 1f)`

### Force 🏋️

Force can be applied on each particle. Two types of force options are available. 
1. Force.Gravity - 

You can apply gravitational force on particle by setting `force = Force.Gravity(magnitude = 2f)`. This way particle will experience a downward force
If you pass the negative value in the magnitude then it'll become an anti-gravity and particle will experience an upward force.

2. Force.Wind - 🎐
You can apply Wind force on each particle in both directions. `force = Force.Wind(1.5f, 0.3f)`. Wind will move particle in the specified direction.

_Default value_ = `Force.Gravity(0.0f)`

### Acceleration 🏃

You can apply acceleration by setting `acceleration = Acceleration(xComponent = 1f, yComponent = 1f)`. Acceleration (x and y component) will be applied uniformly on each frame of animation to a particle.

_Default value_ = `Acceleration(0f, 0f)`

### Particle Size 🌏

You can configure particle size in two ways. 

1. Setting random size of particles

`particleSize = ParticleSize.RandomSizes(25..100)` Set range of sizes and a random size will be applied to each particle.

2. Setting fixed size

`particleSize = ParticleSize.ConstantSize(25f)` sets the constant size to each particle.

_Default value_ = `ParticleSize.ConstantSize(25f)`


### Particle Color 🔶

You can configure random colors or single color to a particle.
1. Random colors - 

`particleColor = ParticleColor.RandomColors(listOf(Color.White, Color.Yellow, Color.Red, Color.Blue))` pass the list of colors and a random value will be selected and applied to different particles.

2. Single color -

`particleColor = ParticleColor.SingleColor(Color.Yellow)` sets the single color to each particle.

_Default value_ = `ParticleColor.SingleColor(Color.Yellow)`


### Lifetime & Aging factor 🧝‍♂️

Set the lifetime of a particle to a value and an aging factor by which the life of particle is reduced in each frame.

`lifeTime = LifeTime(maxLife = 255f, agingFactor = 1f)`

Here at each frame the `againgFactor` will be removed from the `maxlife` value of particle.

_Default value_ = `LifeTime(maxLife = 255f, agingFactor = 1f)`
