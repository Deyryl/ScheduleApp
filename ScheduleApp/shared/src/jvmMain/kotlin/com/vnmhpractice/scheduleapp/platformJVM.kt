package com.vnmhpractice.scheduleapp

class jvmPlatform : Platform {
    override val name: String = "JVM"
}

actual fun getPlatform(): Platform = jvmPlatform()