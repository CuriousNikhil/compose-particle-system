package me.nikhilchaudhari.composeparticlesystem.utils

import android.content.res.Resources

private val DENSITY = Resources.getSystem().displayMetrics.density
fun Int.dp2Px(): Float = (this * DENSITY)
