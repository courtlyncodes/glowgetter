package com.example.glowgetter

import android.app.Application
import com.example.glowgetter.data.DefaultGlowGetterAppContainer
import com.example.glowgetter.data.GlowGetterAppContainer

class GlowGetterApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var glowGetterAppContainer: GlowGetterAppContainer

    override fun onCreate() {
        super.onCreate()
        glowGetterAppContainer = DefaultGlowGetterAppContainer()
    }
}