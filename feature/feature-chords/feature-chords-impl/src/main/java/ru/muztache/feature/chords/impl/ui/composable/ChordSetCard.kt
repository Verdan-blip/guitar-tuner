package ru.muztache.feature.chords.impl.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun ChordSetCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes imageRes: Int
) {
    Column(
        modifier = Modifier
            .width(MuztacheTheme.sizes.extraLarge)
            .then(modifier)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(MuztacheTheme.corners.extraLarge))
                .fillMaxWidth()
                .height(MuztacheTheme.sizes.extraLarge + MuztacheTheme.sizes.medium)
        )
        Text(
            text = title,
            style = MuztacheTheme.typography.titleMedium,
            color = MuztacheTheme.colors.textPrimary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = MuztacheTheme.paddings.normal + MuztacheTheme.paddings.small)
        )
    }
}

@Preview
@Composable
private fun ChordSetCardPreview() {
    MuztacheTheme {
        ChordSetCard(
            title = "Базовые аккорды для начинающих",
            imageRes = ru.muztache.core.theme.R.drawable.photo_placeholder,
        )
    }
}
