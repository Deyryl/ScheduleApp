package com.vnmhpractice.scheduleapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform