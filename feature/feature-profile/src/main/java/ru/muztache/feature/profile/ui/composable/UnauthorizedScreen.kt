@file:Suppress("MagicNumber")
package ru.muztache.feature.profile.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.button.MuztacheTextButton
import ru.muztache.feature.profile.R

@Composable
internal fun UnauthorizedScreen(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        val imageOffsetX = (-120).dp
        val imageOffsetY = 64.dp
        val imageRotation = -30f
        Image(
            painter = painterResource(R.drawable.guitar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(imageOffsetX, imageOffsetY)
                .rotate(degrees = imageRotation)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = MuztacheTheme.paddings.medium)
        ) {
            Text(
                text = stringResource(R.string.use_all_features_with_account),
                style = MuztacheTheme.typography.displayLarge,
                color = MuztacheTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.description),
                style = MuztacheTheme.typography.bodyMedium,
                color = MuztacheTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.extraLarge)
                    .padding(horizontal = MuztacheTheme.paddings.normal),
                horizontalArrangement = Arrangement
                    .spacedBy(MuztacheTheme.paddings.large)
            ) {
                MuztacheTextButton(
                    text = stringResource(R.string.register),
                    onClick = onSignUpClick,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = stringResource(R.string.login),
                    style = MuztacheTheme.typography.labelLarge,
                    color = MuztacheTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .clickable {
                            onSignInClick()
                        }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UnauthorizedScreenPreview() {
    UnauthorizedScreen(
        onSignInClick = { },
        onSignUpClick = {  },
        modifier = Modifier
            .fillMaxSize()
    )
}
