package ru.muztache.guitartuner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.feature.tuner.ui.TunerScreen
import ru.muztache.guitartuner.ui.theme.GuitarTunerTheme

class MainActivity : ComponentActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)

        setContent {
            GuitarTunerTheme {
                MainActivityContent(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MainActivityContent(
    modifier: Modifier = Modifier
) {
    TunerScreen(modifier)
}

@Preview(showSystemUi = true)
@Composable
fun MainActivityContentPreview() {
    GuitarTunerTheme {
        MainActivityContent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}