package me.nikhilchaudhari.compose_particle_system.utils

import android.content.res.Resources

private val DENSITY = Resources.getSystem().displayMetrics.density
fun Int.dp2Px(): Float = (this * DENSITY)
