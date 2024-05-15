package com.example.glowgetter.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.glowgetter.GlowGetterApplication
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            GlowGetterViewModel(
                glowGetterApplication().glowGetterAppContainer.glowGetterRepository,
                glowGetterApplication().glowGetterAppContainer.favoritesRepository,
            )
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [GlowGetterApplication].
 */
fun CreationExtras.glowGetterApplication(): GlowGetterApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GlowGetterApplication)
