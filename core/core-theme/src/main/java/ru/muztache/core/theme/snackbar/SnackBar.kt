package ru.muztache.core.theme.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalMuztacheSnackBar = compositionLocalOf<SnackbarHostState> {
    error("Snackbar host has not been provided")
}