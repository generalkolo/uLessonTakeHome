package com.ulesson.remote.utils

fun String?.safeString(): String = this ?: "N/A"

fun Int?.safeInt(): Int = this ?: 0

fun Double?.safeDouble(): Double = this ?: 0.00
