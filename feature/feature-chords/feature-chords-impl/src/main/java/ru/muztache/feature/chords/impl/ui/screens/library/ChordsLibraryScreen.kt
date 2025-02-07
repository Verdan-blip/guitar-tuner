package ru.muztache.feature.chords.impl.ui.screens.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.feature.chords.R
import ru.muztache.feature.chords.impl.ui.composable.ChordSetCard
import ru.muztache.feature.chords.impl.ui.mvi.ChordsLibraryEvent
import ru.muztache.feature.chords.impl.ui.navigation.InnerRoute

@Composable
fun ChordsLibraryScreen(
    modifier: Modifier = Modifier,
    onNavigateToBaseChords: () -> Unit,
    onNavigateToAdvancedChords: () -> Unit
) {
    val viewModel = koinViewModel<ChordsViewModel>()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    ChordsLibraryScreenContent(
        modifier = modifier,
        onAdvancedChordsClick = {
            viewModel.reducer(ChordsLibraryEvent.AdvancedChordsClick)
        },
        onBaseChordsClick = {
            viewModel.reducer(ChordsLibraryEvent.BaseChordsClick)
        }
    )

    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                when (value.route) {
                    is InnerRoute.BaseChordsDetails -> {
                        onNavigateToBaseChords()
                    }
                    is InnerRoute.AdvancedChordsDetails -> {
                        onNavigateToAdvancedChords()
                    }
                }
            }
            else -> Unit
        }
    }
}

@Composable
private fun ChordsLibraryScreenContent(
    modifier: Modifier = Modifier,
    onBaseChordsClick: () -> Unit,
    onAdvancedChordsClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = MuztacheTheme.paddings.large)
        ) {
            Text(
                text = stringResource(R.string.title_chords_library),
                style = MuztacheTheme.typography.displayLarge,
                color = MuztacheTheme.colors.textPrimary,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.medium)
            )
            Image(
                painter = painterResource(R.drawable.guitarist_poster),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
                    .fillMaxWidth()
                    .height(MuztacheTheme.sizes.extraLarge)
                    .clip(RoundedCornerShape(MuztacheTheme.corners.extraLarge))
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(top = MuztacheTheme.paddings.large),
                horizontalArrangement = Arrangement
                    .spacedBy(MuztacheTheme.paddings.medium)
            ) {
                ChordSetCard(
                    title = stringResource(R.string.basic_chords),
                    imageRes = R.drawable.basic_chords_poster,
                    modifier = Modifier
                        .clickable { onBaseChordsClick() }
                )
                ChordSetCard(
                    title = stringResource(R.string.advanced_chords),
                    imageRes = R.drawable.advanced_chords_poster,
                    modifier = Modifier
                        .clickable { onAdvancedChordsClick() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChordsLibraryScreenPreview() {
    MuztacheTheme {
        MuztacheSurface {
            ChordsLibraryScreenContent(
                onBaseChordsClick = { },
                onAdvancedChordsClick = { },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
