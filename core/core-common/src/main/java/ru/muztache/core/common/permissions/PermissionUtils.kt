package ru.muztache.core.common.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun String.checkPermission(context: Context): Boolean =
    when (ContextCompat.checkSelfPermission(context, this)) {
        PackageManager.PERMISSION_GRANTED -> true
        else -> false
    }

inline fun String.shouldShowRequestPermissionRationale(context: Context, body: (Boolean) -> Unit) {
    body(
        ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, this)
    )
}

@Composable
fun rememberRequestPermissionLauncher(
    body: (Boolean) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
        onResult = body
    )
}
